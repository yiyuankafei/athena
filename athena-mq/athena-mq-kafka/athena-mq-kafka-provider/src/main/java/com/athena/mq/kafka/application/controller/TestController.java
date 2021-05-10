package com.athena.mq.kafka.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @RequestMapping("/test/send")
    public String testSend(String message) {

        System.out.println("准备发送消息：" + message);
        kafkaTemplate.send("testTopic1", message);
        System.out.println("发送消息结束：" + message);
        return "SUCCESS";
    }

}
