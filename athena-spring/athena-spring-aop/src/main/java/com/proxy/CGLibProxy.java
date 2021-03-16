package com.proxy;

import com.service.impl.TestServiceImpl;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.proxy.Enhancer;

public class CGLibProxy {

    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\enhancer");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TestServiceImpl.class);
        enhancer.setCallback(new CGLibCallback());
        TestServiceImpl proxy = (TestServiceImpl) enhancer.create();
        proxy.sleep();
    }

}
