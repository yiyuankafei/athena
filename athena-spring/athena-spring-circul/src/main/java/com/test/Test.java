package com.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.application.AppConfig;

public class Test {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ct = new AnnotationConfigApplicationContext(AppConfig.class);
		
		//关闭循环依赖
		/*AnnotationConfigApplicationContext ct = new AnnotationConfigApplicationContext();
		ct.register(AppConfig.class);
		ct.setAllowCircularReferences(false);
		ct.refresh();*/
		
		
	}

}
