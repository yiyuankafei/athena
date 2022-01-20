package com.athena.mq.ons.producer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/test")
    public String test(String name) {
        return "OnsProducerApplication:" + name;
    }

}
