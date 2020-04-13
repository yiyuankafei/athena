package com.application.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Test {
	
	public static void main(String[] args) {
		
		MyInterface mi = new MyClass();
		mi.execute();
		
		InvocationHandler handler = new MyProxy(mi); 
		
		MyInterface miProxy = (MyInterface)Proxy.newProxyInstance(handler.getClass().getClassLoader(), mi.getClass().getInterfaces(), handler);
		
		miProxy.execute();
		
		
	}

}
