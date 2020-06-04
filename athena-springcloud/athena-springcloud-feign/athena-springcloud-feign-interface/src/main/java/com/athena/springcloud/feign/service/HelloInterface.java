package com.athena.springcloud.feign.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


public interface HelloInterface {
	
	@RequestMapping("/hello")
	String sayHello1(@RequestParam("name") String name);

}
