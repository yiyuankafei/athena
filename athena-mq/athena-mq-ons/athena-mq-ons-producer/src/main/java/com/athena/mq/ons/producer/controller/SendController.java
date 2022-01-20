package com.athena.mq.ons.producer.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.ons.api.bean.ProducerBean;
import com.athena.mq.ons.producer.config.MqConfig;
import com.athena.mq.ons.producer.entity.TestMessageEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@RestController
public class SendController {

    @Resource
    private ProducerBean mqProducer;

    @Resource
    private MqConfig mqConfig;


    @RequestMapping("/send")
    public String send(String name, Integer age) {

        String requestId = System.currentTimeMillis() + "";
        String source = "TEST_TAG";

        TestMessageEntity entity = new TestMessageEntity();
        entity.setAge(age);
        entity.setName(name);
        entity.setRegisterTime(new Date());

        Message msg = new Message(mqConfig.getTopic(), source, requestId, JSONObject.toJSONString(entity).getBytes());
        SendResult sendResult = mqProducer.send(msg);
        return JSON.toJSONString(sendResult);
    }
}
