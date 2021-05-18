package com.athena.mq.kafka.application.helper;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Data
public class QueueName {

    public static QueueName THIS;

    @Value("${topic.accept.youku.policy.sync:}")
    public String ACCEPT_YOU_KU_POLICY_SYNC;
    @Value("${test.endpoint:}")
    private String endpoint;

    @PostConstruct
    public void init() {
        THIS = this;
    }

}