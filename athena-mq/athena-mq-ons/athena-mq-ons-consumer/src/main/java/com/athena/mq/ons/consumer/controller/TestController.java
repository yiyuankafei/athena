package com.athena.mq.ons.consumer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/test")
    public String test(String name) {
        return "OnsConsumerApplication:" + name;
    }

}
