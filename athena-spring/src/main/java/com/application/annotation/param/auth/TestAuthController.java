package com.application.annotation.param.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestAuthController {
	
	@Auth
	@RequestMapping("/test/auth")
	public String testAuth() {
		return "auth success";
	}

}
