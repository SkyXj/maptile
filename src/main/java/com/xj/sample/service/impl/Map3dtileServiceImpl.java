/**   
 * Copyright © 2018武汉中地数码科技有限公司. All rights reserved.
 * 
 * @Title: Map3dtileServiceImpl.java 
 * @Package: com.xj.sample.service.impl
 * @Description: TODO
 * @author: sky-1012262558@qq.com
 * @date: 2019-10-18 09:34:34
 * @Modify Description : 
 * @Modify Person : 
 * @version: V1.0   
 */


package com.xj.sample.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xj.sample.entity.Map3dtile;
import com.xj.sample.mapper.Map3dtileMapper;
import com.xj.sample.service.Map3dtileService;
import com.xj.sample.tool.HttpUtils;
import com.xj.sample.tool.IoUtils;
import com.xj.sample.utils.MathUtils;
import org.jsoup.Connection;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
* 描述： 服务实现层
* @author sky
* @date 2019-10-18 09:34:34
*/
@Service
public class Map3dtileServiceImpl extends ServiceImpl<Map3dtileMapper, Map3dtile> implements Map3dtileService{

	@Override
	public byte[] get3dTile(Integer type, Integer z, Integer x, Integer y) {
 		EntityWrapper<Map3dtile> wrapper = new EntityWrapper<Map3dtile>();
		wrapper.eq("Type", type);
		wrapper.eq("Zoom", z);
		wrapper.eq("X", x);
		wrapper.eq("Y", y);
		Map3dtile map3dtile = selectOne(wrapper);
		if (map3dtile != null) {
			byte[] tile = map3dtile.getTile();
//            return blobToBytes(tile);
			return tile;
		}else{
			byte[] tile=save3dTile(type,z,x,y);
			return tile;
		}
	}

	@Override
	public byte[] update3dTile(Integer type, Integer z, Integer x, Integer y) {
		EntityWrapper<Map3dtile> wrapper=new EntityWrapper<>();
		wrapper.eq("Type", 40800);
		wrapper.eq("Zoom", z);
		wrapper.eq("X", x);
		wrapper.eq("Y", y);
		boolean delete = delete(wrapper);
		byte[] bytes = save3dTile(type, z, x, y);
		return bytes;
	}

	@Override
	public int save3dTile(Integer start, Integer end){
		String url="http://www.google.cn/maps/vt?lyrs=s%40800&x={0}&y={1}&z={2}";
		String annotation="";
		List<Map3dtile> list=new ArrayList<>();
		for (int z = start; z <=end ; z++) {
			for (int x = 0; x < (1<<z); x++) {
				for (int y = 0; y < (1<<z); y++) {
					try {
						EntityWrapper<Map3dtile> wrapper=new EntityWrapper<>();
						wrapper.eq("Type", 40800);
						wrapper.eq("Zoom", z);
						wrapper.eq("X", x);
						wrapper.eq("Y", y);
						int i = selectCount(wrapper);
						if(i>0){
							continue;
						}
						String currenturl= MessageFormat.format(url,x+"",y+"",z);
						Connection.Response response = HttpUtils.get(currenturl);
						BufferedInputStream bufferedInputStream = response.bodyStream();
						byte[] bytes = IoUtils.readStream(bufferedInputStream);
						Map3dtile map3dtile=new Map3dtile();
						map3dtile.setTile(bytes);
						map3dtile.setType(40800);
						map3dtile.setZoom(z);
						map3dtile.setX(x);
						map3dtile.setY(y);
						list.add(map3dtile);
					}catch (Exception e){
						System.out.println(z+"级别("+(x+","+y)+")下载失败");
						continue;
					}
				}
			}
		}
		boolean b = insertBatch(list);
		return list.size();
	}

	@Override
	public byte[] save3dTile(Integer type, Integer z, Integer x, Integer y) {
//		String url="http://www.google.cn/maps/vt?lyrs=s%40800&x={0}&y={1}&z={2}";
		String url="http://mt{s}.google.cn/vt/lyrs=s&hl=zh-CN&x={x}&y={y}&z={z}&s=Gali";
		try {
			String currenturl= MessageFormat.format(url, MathUtils.getRandom(1, 3),x+"",y+"",z);
			Connection.Response response = HttpUtils.get(currenturl);
			BufferedInputStream bufferedInputStream = response.bodyStream();
			byte[] bytes = IoUtils.readStream(bufferedInputStream);
			Map3dtile map3dtile=new Map3dtile();
			map3dtile.setTile(bytes);
			map3dtile.setType(type);
			map3dtile.setZoom(z);
			map3dtile.setX(x);
			map3dtile.setY(y);
			insert(map3dtile);
			return bytes;
		}catch (Exception e){
			return null;
		}
	}

	@Override
	public Map3dtile getMap3dtile(long id) {
		// TODO Auto-generated method stub
		return baseMapper.getMap3dtile(id);
	}
	
	@Override
	public Page<Map3dtile> listMap3dtile(Integer pageNumber, Integer pageSize,String keyword) {
		// TODO Auto-generated method stub
		Page<Map3dtile> page=new Page<Map3dtile>();
		page.setRecords(baseMapper.listMap3dtile((pageNumber-1)*pageSize,pageSize,keyword));
		page.setTotal(baseMapper.getTotal(keyword));
		return page;
	}
}