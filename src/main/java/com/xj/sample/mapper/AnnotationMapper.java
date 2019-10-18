/**   
 * Copyright © 2018武汉中地数码科技有限公司. All rights reserved.
 * 
 * @Title: AnnotationMapper.java 
 * @Package: com.xj.sample.mapper
 * @Description: TODO
 * @author: sky-1012262558@qq.com
 * @date: 2019-10-18 11:09:36
 * @Modify Description : 
 * @Modify Person : 
 * @version: V1.0   
 */

package com.xj.sample.mapper;

import com.xj.sample.entity.Annotation;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;


public interface AnnotationMapper extends BaseMapper<Annotation>{
	Annotation getAnnotation(long id);
	
	List<Annotation> listAnnotation(
			@Param("pageNumber")Integer pageNumber, 
			@Param("pageSize")Integer pageSize,
			@Param("keyword")String keyword);
			
	int getTotal(@Param("keyword")String keyword);
}