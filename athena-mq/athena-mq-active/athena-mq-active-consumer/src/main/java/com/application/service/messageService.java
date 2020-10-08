package com.application.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author 一元咖啡
 * @site www.zhulu.com
 * @create 2020-10-06 16:33
 */
@Service
public class messageService {

    @JmsListener(destination = "test-q2", concurrency = "5")
    public void messageConsumer(String message) {

        System.out.println("aaaa111ffabbbccc");
        System.out.println("消费者收到消息test-q2：" + message);

    }
}
