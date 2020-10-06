package com.application.controller;

import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 一元咖啡
 * @site www.zhulu.com
 * @create 2020-10-06 16:34
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    @RequestMapping("/send")
    public String send(String message) {

        System.out.println("准备发送消息到test-q2：" + message);
        jmsMessagingTemplate.convertAndSend("test-q2", message);
        return "send success";
    }
}
