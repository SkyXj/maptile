/**   
 * Copyright © 2018武汉中地数码科技有限公司. All rights reserved.
 * 
 * @Title: Map3dtileMapper.java 
 * @Package: com.xj.sample.mapper
 * @Description: TODO
 * @author: sky-1012262558@qq.com
 * @date: 2019-10-18 09:34:34
 * @Modify Description : 
 * @Modify Person : 
 * @version: V1.0   
 */

package com.xj.sample.mapper;

import com.xj.sample.entity.Map3dtile;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;


public interface Map3dtileMapper extends BaseMapper<Map3dtile>{
	Map3dtile getMap3dtile(long id);
	
	List<Map3dtile> listMap3dtile(
			@Param("pageNumber")Integer pageNumber, 
			@Param("pageSize")Integer pageSize,
			@Param("keyword")String keyword);
			
	int getTotal(@Param("keyword")String keyword);
}