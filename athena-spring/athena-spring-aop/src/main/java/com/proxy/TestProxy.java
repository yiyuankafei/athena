package com.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestProxy {

    private Object target;

    public TestProxy(Object target) {
        this.target = target;
    }

    public Object getProxy() {
        //Class<?>[] interfaces = target.getClass().getInterfaces();
        Class<?>[] interfaces = target.getClass().getInterfaces();
        System.out.println(interfaces.length);
        Class<?> anInterface1 = interfaces[1];
        Class<?> anInterface2 = interfaces[2];
        Class<?>[] proxyInterfaces = new Class[]{anInterface1, anInterface2};


        return Proxy.newProxyInstance(target.getClass().getClassLoader(), proxyInterfaces,
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("开始前");
                        method.invoke(target, args);
                        System.out.println("开始后");
                        return null;
                    }
                });
    }

}
