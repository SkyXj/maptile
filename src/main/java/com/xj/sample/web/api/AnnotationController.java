/**   
 * Copyright © 2018武汉中地数码科技有限公司. All rights reserved.
 * 
 * @Title: AnnotationController.java 
 * @Package: com.xj.sample.web.api
 * @Description: TODO
 * @author: sky-1012262558@qq.com
 * @date: 2019-10-18 11:09:36
 * @Modify Description : 
 * @Modify Person : 
 * @version: V1.0   
 */

package com.xj.sample.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.common.entity.Response;
import com.common.entity.ResultCode;
import com.common.mybatis.baseapi.AbstractController;
import com.xj.sample.entity.Annotation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.xj.sample.service.AnnotationService;

@Api(value="",tags={""})
@RestController
@RequestMapping("/annotation")
@CrossOrigin
public class AnnotationController extends AbstractController<Annotation>{
	
	@Autowired
	AnnotationService annotationService;


	@GetMapping(value="/getAnnotation/{type}/{x}/{y}/{z}",produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<byte[]> getAnnotation(@PathVariable("type") Integer type, @PathVariable("x") Integer x, @PathVariable("y") Integer y, @PathVariable("z") Integer z ){
		try {
			return new ResponseEntity<byte[]>(annotationService.getAnnotation(type,z,x,y), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<byte[]>(HttpStatus.FAILED_DEPENDENCY);
		}
	}

	@GetMapping(value="/saveAnnotation")
	public ResponseEntity<Integer> saveAnnotation(@RequestParam Integer start,@RequestParam Integer end){
		try {
			return new ResponseEntity<Integer>(annotationService.saveAnnotation(start,end), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<Integer>(HttpStatus.FAILED_DEPENDENCY);
		}
	}


	
	@ApiOperation("根据id查找(关联对象)")
	@RequestMapping(value="/getAnnotation/{id}",method=RequestMethod.GET)
	public Response getAnnotation(@PathVariable(value="id") long id){
		try {
			return result(annotationService.getAnnotation(id));
		} catch (Exception e) {
			// TODO: handle exception
			return result(ResultCode.falied,e.getMessage());
		}
	}
	
	@ApiOperation("查所有(关联对象)")
	@RequestMapping(value="/listAnnotation/{pageNumber}/{pageSize}",method=RequestMethod.POST)
	public Response listAnnotation(@PathVariable(value="pageNumber") Integer pageNumber,
			@PathVariable(value="pageSize") Integer pageSize,@RequestParam(required=false) String keyword){
		try {
			return result(annotationService.listAnnotation(pageNumber,pageSize,keyword));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return result(ResultCode.falied,e.getMessage());
		}
	}
}