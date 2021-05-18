package com.athena.mq.kafka.application.controller;

import com.athena.mq.kafka.application.helper.KafkaHelper;
import com.athena.mq.kafka.application.helper.QueueName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    /*@Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;*/

    @Autowired
    private KafkaHelper kafkaHelper;

    @RequestMapping("/test/send")
    public String testSend(String message) {

        System.out.println("准备发送消息：" + message);
        System.out.println("发送目的地：" + QueueName.THIS.ACCEPT_YOU_KU_POLICY_SYNC);
        kafkaHelper.sendAck(QueueName.THIS.ACCEPT_YOU_KU_POLICY_SYNC, message);
        System.out.println("发送消息结束：" + message);
        return "SUCCESS";
    }

}
