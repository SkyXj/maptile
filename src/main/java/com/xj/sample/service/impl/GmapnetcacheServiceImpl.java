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
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.sql.Blob;

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
        if (gmapnetcache != null) {
            byte[] tile = gmapnetcache.getTile();
//            return blobToBytes(tile);
            return tile;
        }
        return null;
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