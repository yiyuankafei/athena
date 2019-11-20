package com.application.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.application.service.BookService;

@RestController
public class BookController {
	
	@Reference(group = "test-nacos2")
	BookService bookService;
	
	@RequestMapping("/get")
	public String getBook(String name) {
		return bookService.getBook(name);
	}

}
