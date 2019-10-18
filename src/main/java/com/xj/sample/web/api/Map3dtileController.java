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
import com.common.entity.Response;
import com.common.entity.ResultCode;
import com.common.mybatis.baseapi.AbstractController;
import com.xj.sample.entity.Map3dtile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.xj.sample.service.Map3dtileService;

@Api(value="",tags={""})
@RestController
@RequestMapping("/map3dtile")
@CrossOrigin
public class Map3dtileController extends AbstractController<Map3dtile>{
	
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


	
	@ApiOperation("根据id查找(关联对象)")
	@RequestMapping(value="/getMap3dtile/{id}",method=RequestMethod.GET)
	public Response getMap3dtile(@PathVariable(value="id") long id){
		try {
			return result(map3dtileService.getMap3dtile(id));
		} catch (Exception e) {
			// TODO: handle exception
			return result(ResultCode.falied,e.getMessage());
		}
	}
	
	@ApiOperation("查所有(关联对象)")
	@RequestMapping(value="/listMap3dtile/{pageNumber}/{pageSize}",method=RequestMethod.POST)
	public Response listMap3dtile(@PathVariable(value="pageNumber") Integer pageNumber,
			@PathVariable(value="pageSize") Integer pageSize,@RequestParam(required=false) String keyword){
		try {
			return result(map3dtileService.listMap3dtile(pageNumber,pageSize,keyword));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return result(ResultCode.falied,e.getMessage());
		}
	}
}