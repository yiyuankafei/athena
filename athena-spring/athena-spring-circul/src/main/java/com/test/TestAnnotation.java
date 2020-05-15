package com.test;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.application.AppConfig;
import com.service.D;

public class TestAnnotation {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ct1 = new AnnotationConfigApplicationContext(AppConfig.class);
		
		//关闭循环依赖
		AnnotationConfigApplicationContext ct = new AnnotationConfigApplicationContext();
		BeanDefinitionBuilder genericBeanDefinition = BeanDefinitionBuilder.genericBeanDefinition(D.class);
		genericBeanDefinition.addPropertyValue("name", "wade");
		genericBeanDefinition.addPropertyValue("age", 16);
		ct.register(AppConfig.class);
		//ct.setAllowCircularReferences(false);
		ct.refresh();
		
		try {
			Object bean = ct.getBean("d");
			D d = (D)bean;
			System.out.println(d.getName());
			System.out.println(d.getAge());
		} catch (Exception e) {
			System.out.println("no d in container");
		}
		
		
		ct.registerBeanDefinition("d", genericBeanDefinition.getBeanDefinition());

		D d2 = (D)ct.getBean("d");
		System.out.println(d2.getName());
		System.out.println(d2.getAge());
		D d3 = (D)ct.getBean("d");
		
		System.out.println("====");
		//System.out.println(d == d2);
		System.out.println(d2 == d3);
		
	}

}
