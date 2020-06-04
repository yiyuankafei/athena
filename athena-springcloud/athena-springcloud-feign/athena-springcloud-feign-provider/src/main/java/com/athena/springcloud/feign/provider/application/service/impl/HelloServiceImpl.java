package com.athena.springcloud.feign.provider.application.service.impl;

import org.springframework.stereotype.Service;

import com.athena.springcloud.feign.provider.application.service.HelloService;

@Service
public class HelloServiceImpl implements HelloService {

	@Override
	public String sayHello(String name) {
		return "hello from feign provider:" + name;
	}

}
