package com.athena.mq.ons.consumer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "mq")
@Data
public class MqConfig {

    private String groupId;

    private String accessKeyId;

    private String accessKeySecret;

    private String topic;

    private String model;

    private String nameSrvAddr;

}
