package com.test;

import com.proxy.TestProxy;
import com.service.TestService;
import com.service.impl.TestServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class TestApplication {
    public static void main(String[] args) {
        /*ConfigurableApplicationContext context = SpringApplication.run(TestApplication.class, args);
        TestService testService = context.getBean(TestService.class);
        System.out.println("testService = " + testService.getClass());*/

        TestServiceImpl testService = new TestServiceImpl();
        testService.sleep();

        TestProxy proxy = new TestProxy(testService);
        TestService proxyService = (TestService) proxy.getProxy();
        proxyService.sleep();
        proxyService.eat();
        proxyService.talk();


    }
}
