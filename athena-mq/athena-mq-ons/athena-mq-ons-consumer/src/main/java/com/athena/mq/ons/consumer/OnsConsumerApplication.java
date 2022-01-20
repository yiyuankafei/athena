package com.athena.mq.ons.consumer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class OnsConsumerApplication extends SpringBootServletInitializer implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(OnsConsumerApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("OnsConsumerApplication启动成功...");
    }
}
