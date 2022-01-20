package com.athena.mq.ons.producer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class OnsProducerApplication extends SpringBootServletInitializer implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(OnsProducerApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("OnsProducerApplication启动成功...");
    }
}
