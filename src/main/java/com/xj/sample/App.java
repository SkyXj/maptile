package com.xj.sample;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@MapperScan("com.xj.sample.mapper")
@RestController
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
