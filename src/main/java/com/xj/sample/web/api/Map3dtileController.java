/**   
 * Copyright © 2018武汉中地数码科技有限公司. All rights reserved.
 * 
 * @Title: Map3dtileController.java 
 * @Package: com.xj.sample.web.api
 * @Description: TODO
 * @author: sky-1012262558@qq.com
 * @date: 2019-10-18 09:34:34
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
import com.xj.sample.entity.Map3dtile;
import com.xj.sample.service.Map3dtileService;


@RestController
@RequestMapping("/map3dtile")
@CrossOrigin
public class Map3dtileController{
	
	@Autowired
	Map3dtileService map3dtileService;

	@GetMapping(value="/get3dTile/{type}/{x}/{y}/{z}",produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<byte[]> get3dTile(@PathVariable("type") Integer type, @PathVariable("x") Integer x, @PathVariable("y") Integer y,@PathVariable("z") Integer z ){
		try {
			return new ResponseEntity<byte[]>(map3dtileService.get3dTile(type,z,x,y), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<byte[]>(HttpStatus.FAILED_DEPENDENCY);
		}
	}

	@GetMapping(value="/update3dTile/{type}/{x}/{y}/{z}",produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<byte[]> update3dTile(@PathVariable("type") Integer type, @PathVariable("x") Integer x, @PathVariable("y") Integer y,@PathVariable("z") Integer z ){
		try {
			return new ResponseEntity<byte[]>(map3dtileService.update3dTile(type,z,x,y), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<byte[]>(HttpStatus.FAILED_DEPENDENCY);
		}
	}

	@GetMapping(value="/save3dTile")
	public ResponseEntity<Integer> save3dTile(@RequestParam Integer start,@RequestParam Integer end){
		try {
			return new ResponseEntity<Integer>(map3dtileService.save3dTile(start,end), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<Integer>(HttpStatus.FAILED_DEPENDENCY);
		}
	}

}