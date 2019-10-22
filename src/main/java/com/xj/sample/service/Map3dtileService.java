/**   
 * Copyright © 2018武汉中地数码科技有限公司. All rights reserved.
 * 
 * @Title: Map3dtileService.java 
 * @Package: com.xj.sample.service
 * @Description: TODO
 * @author: sky-1012262558@qq.com
 * @date: 2019-10-18 09:34:34
 * @Modify Description : 
 * @Modify Person : 
 * @version: V1.0   
 */

package com.xj.sample.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.xj.sample.entity.Map3dtile;

/**
* 描述： 服务实现层接口
* @author sky
* @date 2019-10-18 09:34:34
*/
public interface Map3dtileService extends IService<Map3dtile> {

	byte[] get3dTile(Integer type,Integer z, Integer x, Integer y);

	byte[] update3dTile(Integer type,Integer z, Integer x, Integer y);

	int save3dTile(Integer start,Integer end);

	byte[] save3dTile(Integer type,Integer z, Integer x, Integer y);

	Map3dtile getMap3dtile(long id);
	
	Page<Map3dtile> listMap3dtile(Integer pageNumber, Integer pageSize,String keyword);
}