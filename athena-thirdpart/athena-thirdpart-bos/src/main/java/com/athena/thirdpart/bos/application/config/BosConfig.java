package com.athena.thirdpart.bos.application.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "bos")
@Data
public class BosConfig {

    private String accessKey;

    private String secretKey;

    private String bucketName;

    private String endpoint;
}
