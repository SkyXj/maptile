/**   
 * Copyright © 2018武汉中地数码科技有限公司. All rights reserved.
 * 
 * @Title: GmapnetcacheController.java 
 * @Package: com.xj.sample.web.api
 * @Description: TODO
 * @author: sky-1012262558@qq.com
 * @date: 2019-10-15 10:36:40
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
import com.xj.sample.entity.Gmapnetcache;

import com.xj.sample.service.GmapnetcacheService;

@RestController
@RequestMapping("/gmapnetcache")
@CrossOrigin
public class GmapnetcacheController{
	
	@Autowired
	GmapnetcacheService gmapnetcacheService;


	@GetMapping(value="/getTile/{type}/{z}/{x}/{y}",produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<byte[]> getTile(@PathVariable("type") Integer type, @PathVariable("z") Integer z, @PathVariable("x") Integer x, @PathVariable("y") Integer y ){
		try {
			return new ResponseEntity<byte[]>(gmapnetcacheService.getTile(type,z,x,y), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<byte[]>(HttpStatus.FAILED_DEPENDENCY);
		}
	}
}