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
import com.common.entity.Response;
import com.common.entity.ResultCode;
import com.common.mybatis.baseapi.AbstractController;
import com.xj.sample.entity.Gmapnetcache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.xj.sample.service.GmapnetcacheService;

@Api(value="",tags={""})
@RestController
@RequestMapping("/gmapnetcache")
@CrossOrigin
public class GmapnetcacheController extends AbstractController<Gmapnetcache>{
	
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
	
	@ApiOperation("根据id查找(关联对象)")
	@RequestMapping(value="/getGmapnetcache/{id}",method=RequestMethod.GET)
	public Response getGmapnetcache(@PathVariable(value="id") long id){
		try {
			return result(gmapnetcacheService.getGmapnetcache(id));
		} catch (Exception e) {
			// TODO: handle exception
			return result(ResultCode.falied,e.getMessage());
		}
	}
	
	@ApiOperation("查所有(关联对象)")
	@RequestMapping(value="/listGmapnetcache/{pageNumber}/{pageSize}",method=RequestMethod.POST)
	public Response listGmapnetcache(@PathVariable(value="pageNumber") Integer pageNumber,
			@PathVariable(value="pageSize") Integer pageSize,@RequestParam(required=false) String keyword){
		try {
			return result(gmapnetcacheService.listGmapnetcache(pageNumber,pageSize,keyword));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return result(ResultCode.falied,e.getMessage());
		}
	}
}