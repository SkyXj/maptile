/**   
 * Copyright © 2018武汉中地数码科技有限公司. All rights reserved.
 * 
 * @Title: GmapnetcacheService.java 
 * @Package: com.xj.sample.service
 * @Description: TODO
 * @author: sky-1012262558@qq.com
 * @date: 2019-10-15 10:36:40
 * @Modify Description : 
 * @Modify Person : 
 * @version: V1.0   
 */

package com.xj.sample.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.xj.sample.entity.Gmapnetcache;

/**
* 描述： 服务实现层接口
* @author sky
* @date 2019-10-15 10:36:40
*/
public interface GmapnetcacheService extends IService<Gmapnetcache> {

	byte[] getTile(Integer type,Integer z, Integer x, Integer y);

	Gmapnetcache getGmapnetcache(long id);
	
	Page<Gmapnetcache> listGmapnetcache(Integer pageNumber, Integer pageSize,String keyword);
}