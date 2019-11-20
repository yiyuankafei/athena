package com.application.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.application.service.BookService;

@Component
@Service(group = "test-nacos2", retries = 0, timeout = 10000)
public class BookServiceImpl implements BookService {

	@Override
	public String getBook(String name) {
		if (name.equals("java")) {
			return "java编程思想22";
		} else if (name.equals("jquery")) {
			return "锋利的JQuery";
		} else {
			return "未知书籍";
		}
	}

}
