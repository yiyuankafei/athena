package com.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.service.E;

public class TestXml {
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:application.xml");
		E e = (E)context.getBean("e");
		e.print();
		
	}

}
