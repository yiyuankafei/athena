package com.athena.spring.context.test;

import com.athena.spring.context.AppConfig;
import com.athena.spring.context.service.Service1;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestContext {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Service1 service1 = (Service1)context.getBean("service1");
        service1.print("marian");
    }


}
