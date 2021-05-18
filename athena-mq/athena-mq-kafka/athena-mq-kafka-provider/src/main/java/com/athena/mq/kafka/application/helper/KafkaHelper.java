package com.athena.mq.kafka.application.helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class KafkaHelper {

    static class AckFlag {
        volatile Boolean flag;

        public boolean isAck() {
            while (flag == null) {
                System.out.println("ack循环---");
            }
            return flag;
        }
    }

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * 发送确认
     *
     * @param topic
     * @param data
     */
    public void sendAck(String topic, Object data) {
        KafkaHelper.AckFlag ackFlag = new KafkaHelper.AckFlag();
        try {
            kafkaTemplate.send(topic, data).addCallback(success -> {
                System.out.println("success-callback======================");
                ackFlag.flag = true;
            }, failure -> {
                System.out.println("fail-callback$$$$$$$$$$$$$$$$$$$$$$$$$");
                ackFlag.flag = false;
                log.error(failure.getMessage(), failure);
            });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("发送失败");
        }
        if (!ackFlag.isAck()) throw new RuntimeException("ack失败");
    }
}

