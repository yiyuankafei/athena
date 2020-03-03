package com.application.annotation.param;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestParamController {
	
	
	@RequestMapping("/test/param")
	public String test(@LoginUser User user) {
		log.info("注入用户信息：{}", user);
		return user.getName();
	}

}
