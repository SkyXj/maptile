/**   
 * Copyright © 2018武汉中地数码科技有限公司. All rights reserved.
 * 
 * @Title: AnnotationService.java 
 * @Package: com.xj.sample.service
 * @Description: TODO
 * @author: sky-1012262558@qq.com
 * @date: 2019-10-18 11:09:36
 * @Modify Description : 
 * @Modify Person : 
 * @version: V1.0   
 */

package com.xj.sample.service;


import com.xj.sample.entity.Annotation;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
/**
* 描述： 服务实现层接口
* @author sky
* @date 2019-10-18 11:09:36
*/
public interface AnnotationService extends IService<Annotation> {

	byte[] getAnnotation(Integer type,Integer z, Integer x, Integer y);

	int saveAnnotation(Integer start,Integer end);

	byte[] saveAnnotation(Integer type,Integer z, Integer x, Integer y);

	Annotation getAnnotation(long id);
	
	Page<Annotation> listAnnotation(Integer pageNumber, Integer pageSize,String keyword);
}