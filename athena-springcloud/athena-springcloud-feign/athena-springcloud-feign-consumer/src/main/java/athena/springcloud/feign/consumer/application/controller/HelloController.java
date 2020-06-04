package athena.springcloud.feign.consumer.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import athena.springcloud.feign.consumer.application.service.ConsumerHelloService;

@RestController
public class HelloController {
	
	@Autowired
	ConsumerHelloService service;
	
	@GetMapping("/consumerHello")
	public String consumerHello(String name) {
		System.out.println("name:" + name);
		return service.sayHello1(name);
	}

}
