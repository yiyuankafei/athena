package com.application.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;

@RestController
@NacosPropertySource(dataId="example", groupId="DEFAULT_GROUP", autoRefreshed=true)
public class HelloController {
	
	@NacosValue(value="${xxxx}", autoRefreshed=true)
	private String info;
	
	@RequestMapping("/hello")
	public String hello() {
		return "hello:" + info;
	}

}
