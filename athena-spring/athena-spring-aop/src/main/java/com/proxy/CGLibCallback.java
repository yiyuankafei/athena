package com.proxy;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CGLibCallback implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy proxy) throws Throwable {
        System.out.println("cglib增强前");
        //注意这里的方法调用，不是用反射哦！！！
        Object object = proxy.invokeSuper(o, objects);
        System.out.println("cglib增强后");
        return object;
    }
}
