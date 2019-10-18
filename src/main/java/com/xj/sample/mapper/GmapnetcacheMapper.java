/**   
 * Copyright © 2018武汉中地数码科技有限公司. All rights reserved.
 * 
 * @Title: GmapnetcacheMapper.java 
 * @Package: com.xj.sample.mapper
 * @Description: TODO
 * @author: sky-1012262558@qq.com
 * @date: 2019-10-15 10:36:40
 * @Modify Description : 
 * @Modify Person : 
 * @version: V1.0   
 */

package com.xj.sample.mapper;

import com.xj.sample.entity.Gmapnetcache;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;


public interface GmapnetcacheMapper extends BaseMapper<Gmapnetcache>{
	Gmapnetcache getGmapnetcache(long id);
	
	List<Gmapnetcache> listGmapnetcache(
			@Param("pageNumber")Integer pageNumber, 
			@Param("pageSize")Integer pageSize,
			@Param("keyword")String keyword);
			
	int getTotal(@Param("keyword")String keyword);
}