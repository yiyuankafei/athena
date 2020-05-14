package com.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class C implements ApplicationContextAware, InitializingBean{
	
	@Autowired
	A a;
	
	public C() {
		System.out.println("call  c  create！！");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		System.out.println("call  c  aware！！");
	}
	
	@PostConstruct
	public void xxx() {
		System.out.println("call  c  postContruct！！");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("call  c  afterPropertiesSet！！");
	}

}
