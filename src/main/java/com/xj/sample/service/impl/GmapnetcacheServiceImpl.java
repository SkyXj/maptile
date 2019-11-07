/**
 * Copyright © 2018武汉中地数码科技有限公司. All rights reserved.
 *
 * @Title: GmapnetcacheServiceImpl.java
 * @Package: com.xj.sample.service.impl
 * @Description: TODO
 * @author: sky-1012262558@qq.com
 * @date: 2019-10-15 10:36:40
 * @Modify Description :
 * @Modify Person :
 * @version: V1.0
 */


package com.xj.sample.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xj.sample.entity.Gmapnetcache;
import com.xj.sample.mapper.GmapnetcacheMapper;
import com.xj.sample.service.GmapnetcacheService;
import com.xj.sample.tool.HttpUtils;
import com.xj.sample.tool.IoUtils;
import org.jsoup.Connection;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.text.MessageFormat;

/**
 * 描述： 服务实现层
 * @author sky
 * @date 2019-10-15 10:36:40
 */
@Service
public class GmapnetcacheServiceImpl extends ServiceImpl<GmapnetcacheMapper, Gmapnetcache> implements GmapnetcacheService {

    @Override
    public byte[] getTile(Integer type, Integer z, Integer x, Integer y) {
        EntityWrapper<Gmapnetcache> wrapper = new EntityWrapper<Gmapnetcache>();
        wrapper.eq("Type", type);
        wrapper.eq("Zoom", z);
        wrapper.eq("X", x);
        wrapper.eq("Y", y);
        Gmapnetcache gmapnetcache = selectOne(wrapper);
        byte[] tile=null;
        if (gmapnetcache != null) {
            tile= gmapnetcache.getTile();
        }else {
            tile=saveTile(type,x,y,z);
        }
        return tile;
    }

    @Override
    public byte[] saveTile(Integer type, Integer x, Integer y,Integer z) {
        String url="http://webrd01.is.autonavi.com/appmaptile?lang=zh_cn&size=1&scale=1&style=8&x={0}&y={1}&z={2}";
        String currenturl= MessageFormat.format(url,x+"",y+"",z);
        Connection.Response response = null;
        try {
            response = HttpUtils.get(currenturl);
            BufferedInputStream bufferedInputStream = response.bodyStream();
            byte[] bytes = IoUtils.readStream(bufferedInputStream);
            Gmapnetcache gmapnetcache=new Gmapnetcache();
            gmapnetcache.setTile(bytes);
            gmapnetcache.setType(type);
            gmapnetcache.setX(x);
            gmapnetcache.setY(y);
            gmapnetcache.setZoom(z);
            insert(gmapnetcache);
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    @Override
    public Gmapnetcache getGmapnetcache(long id) {
        // TODO Auto-generated method stub
        return baseMapper.getGmapnetcache(id);
    }

    @Override
    public Page<Gmapnetcache> listGmapnetcache(Integer pageNumber, Integer pageSize, String keyword) {
        // TODO Auto-generated method stub
        Page<Gmapnetcache> page = new Page<Gmapnetcache>();
        page.setRecords(baseMapper.listGmapnetcache((pageNumber - 1) * pageSize, pageSize, keyword));
        page.setTotal(baseMapper.getTotal(keyword));
        return page;
    }


}