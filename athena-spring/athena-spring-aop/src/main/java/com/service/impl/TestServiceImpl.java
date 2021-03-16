package com.service.impl;

import com.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Override
    public void eat() {
        System.out.println("eat 2 meals one day");
    }

    @Override
    public void sleep() {
        System.out.println("sleep after 12 pm;");
    }

    @Override
    public void talk() {
        System.out.println("talk is cheap");
    }
}
