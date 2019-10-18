package com.xj.sample;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import xj.starter.generate.util.GenerateService;

@SpringBootApplication
@MapperScan("com.xj.sample.mapper")
@RestController
public class App {
	
	@Autowired
	private GenerateService generateService;
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
//	@GetMapping("/generate")
//	public boolean generate() throws Exception {
//		generateService.codeGenerate();
//		return true;
//	}
}
