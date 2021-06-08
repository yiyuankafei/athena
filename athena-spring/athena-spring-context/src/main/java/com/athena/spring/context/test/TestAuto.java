package com.athena.spring.context.test;

import com.athena.spring.context.AppConfig;
import com.athena.spring.context.bean.A;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestAuto {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        A a = (A)context.getBean("a");
        a.printB();
    }

}
