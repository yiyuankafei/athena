package com.athena.springcloud.feign.provider.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.athena.springcloud.feign.provider.application.service.HelloService;
import com.athena.springcloud.feign.service.HelloInterface;

@RestController
public class HelloController implements HelloInterface {
	
	@Autowired
	HelloService helloService;

	@Override
	public String sayHello1(String name) {
		return helloService.sayHello(name);
	}

}
