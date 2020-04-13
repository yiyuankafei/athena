package com.application.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyProxy implements InvocationHandler {
	
	private Object target;
	
	public MyProxy(MyInterface target) {
		super();
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaa");
		Object invoke = method.invoke(target, args);
		System.out.println("bbbbbbbbbbbbbbbbbbbbbbbb");
		return invoke;
	}

}
