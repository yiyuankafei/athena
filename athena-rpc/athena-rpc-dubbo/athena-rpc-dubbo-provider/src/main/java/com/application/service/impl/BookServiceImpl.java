package com.application.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.application.service.BookService;

@Component
@Service
public class BookServiceImpl implements BookService {

	@Override
	public String getBook(String name) {
		if (name.equals("java")) {
			return "java编程思想";
		} else if (name.equals("jquery")) {
			return "锋利的JQuery";
		} else {
			return "未知书籍";
		}
	}

}
