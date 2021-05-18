package com.athena.mq.kafka.consumer.application.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TestConsumer {

    //@KafkaListener(topics = {"testTopic1"})
    //@KafkaListener(topics = {"ACCEPT_YOU_KU_POLICY_SYNC_TEST"})
    @KafkaListener(topics = "#{'ACCEPT_YOU_KU_POLICY_SYNC_TEST'.split(',')}")
    public void onMessage(ConsumerRecord<?, ?> record) {
        System.out.println("简单消费："+ record.topic()+"-"+record.partition()+"-"+record.value());
    }

}
