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
import com.xj.sample.entity.Annotation;

import com.xj.sample.service.AnnotationService;

@RestController
@RequestMapping("/annotation")
@CrossOrigin
public class AnnotationController{
	
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
}