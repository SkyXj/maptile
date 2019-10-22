/**   
 * Copyright © 2018武汉中地数码科技有限公司. All rights reserved.
 * 
 * @Title: AnnotationServiceImpl.java 
 * @Package: com.xj.sample.service.impl
 * @Description: TODO
 * @author: sky-1012262558@qq.com
 * @date: 2019-10-18 11:09:36
 * @Modify Description : 
 * @Modify Person : 
 * @version: V1.0   
 */


package com.xj.sample.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.xj.sample.tool.HttpUtils;
import org.jsoup.Connection;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.plugins.Page;
import com.xj.sample.entity.Annotation;
import com.xj.sample.mapper.AnnotationMapper;
import com.xj.sample.service.AnnotationService;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
* 描述： 服务实现层
* @author sky
* @date 2019-10-18 11:09:36
*/
@Service
public class AnnotationServiceImpl extends ServiceImpl<AnnotationMapper, Annotation> implements AnnotationService{

	@Override
	public byte[] getAnnotation(Integer type, Integer z, Integer x, Integer y) {
		EntityWrapper<Annotation> wrapper = new EntityWrapper<Annotation>();
		wrapper.eq("Type", type);
		wrapper.eq("Zoom", z);
		wrapper.eq("X", x);
		wrapper.eq("Y", y);
		Annotation annotation = selectOne(wrapper);
		if (annotation != null) {
			byte[] tile = annotation.getTile();
			return tile;
		}else{
			byte[] tile = saveAnnotation(type, z, x, y);
			return tile;
		}
	}

	@Override
	public int saveAnnotation(Integer start, Integer end){
		String url="http://t0.tianditu.com/cia_w/wmts?service=wmts&request=GetTile&version=1.0.0&LAYER=cia&tileMatrixSet=w&TileMatrix={0}&TileRow={1}&TileCol={2}&style=default.jpg&tk=319727f59d0d5780a8b984f68c57cd68";
		List<Annotation> list=new ArrayList<Annotation>();
		for (int z = start; z <=end ; z++) {
			for (int x = 0; x < (1<<z); x++) {
				for (int y = 0; y < (1<<z); y++) {
					try {
						EntityWrapper<Annotation> wrapper=new EntityWrapper<Annotation>();
						wrapper.eq("Type", 19940824);
						wrapper.eq("Zoom", z);
						wrapper.eq("X", x);
						wrapper.eq("Y", y);
						int i = selectCount(wrapper);
						if(i>0){
							continue;
						}
						String currenturl= MessageFormat.format(url,z,x+"",y+"");
						Connection.Response response = HttpUtils.get(currenturl);
						BufferedInputStream bufferedInputStream = response.bodyStream();
						byte[] bytes = readStream(bufferedInputStream);
						Annotation annotation=new Annotation();
						annotation.setTile(bytes);
						annotation.setType(19940824);
						annotation.setZoom(z);
						annotation.setX(x);
						annotation.setY(y);
						list.add(annotation);
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
	public byte[] saveAnnotation(Integer type, Integer z, Integer x, Integer y) {
		String url="http://t0.tianditu.com/cia_w/wmts?service=wmts&request=GetTile&version=1.0.0&LAYER=cia&tileMatrixSet=w&TileMatrix={0}&TileRow={1}&TileCol={2}&style=default.jpg&tk=319727f59d0d5780a8b984f68c57cd68";
		String currenturl= MessageFormat.format(url,z,x+"",y+"");
		try {
			Connection.Response response = HttpUtils.get(currenturl);
			BufferedInputStream bufferedInputStream = response.bodyStream();
			byte[] bytes = readStream(bufferedInputStream);
			Annotation annotation=new Annotation();
			annotation.setTile(bytes);
			annotation.setType(19940824);
			annotation.setZoom(z);
			annotation.setX(x);
			annotation.setY(y);
			insert(annotation);
			return bytes;
		}catch (Exception e){
			return null;
		}
	}

	public static byte[] readStream(InputStream inStream) throws Exception{
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = -1;
		while((len = inStream.read(buffer)) != -1){
			outStream.write(buffer, 0, len);
		}
		outStream.close();
		inStream.close();
		return outStream.toByteArray();
	}


	@Override
	public Annotation getAnnotation(long id) {
		// TODO Auto-generated method stub
		return baseMapper.getAnnotation(id);
	}
	
	@Override
	public Page<Annotation> listAnnotation(Integer pageNumber, Integer pageSize,String keyword) {
		// TODO Auto-generated method stub
		Page<Annotation> page=new Page<Annotation>();
		page.setRecords(baseMapper.listAnnotation((pageNumber-1)*pageSize,pageSize,keyword));
		page.setTotal(baseMapper.getTotal(keyword));
		return page;
	}
}