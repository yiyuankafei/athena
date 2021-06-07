package com.athena.spring.context.service;

import org.springframework.stereotype.Component;

@Component
public class Service1 {

    public void print(String name) {
        System.out.println("service1 name:" + name);
    }

}
