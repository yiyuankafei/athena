package com.athena.mq.ons.producer.config;

import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.bean.ConsumerBean;
import com.aliyun.openservices.ons.api.bean.ProducerBean;
import com.aliyun.openservices.shade.org.apache.commons.lang3.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.Properties;

@Configuration
@Slf4j
public class ProducerConfig {

    @Resource
    private MqConfig mqConfig;

    public static final String MQ_PRODUCER = "mqProducer";

    @Bean(name = MQ_PRODUCER, initMethod = "start", destroyMethod = "shutdown")
    public ProducerBean mqProducer() {
        return createMqProducer();
    }

    public ProducerBean createMqProducer() {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.GROUP_ID, mqConfig.getGroupId());
        properties.put(PropertyKeyConst.AccessKey, mqConfig.getAccessKeyId());
        properties.put(PropertyKeyConst.SecretKey, mqConfig.getAccessKeySecret());
        //设置发送超时时间，单位毫秒
        properties.setProperty(PropertyKeyConst.SendMsgTimeoutMillis, "3000");
        properties.put(PropertyKeyConst.NAMESRV_ADDR, mqConfig.getNameSrvAddr());
        ProducerBean mqProducer = new ProducerBean();
        mqProducer.setProperties(properties);
        log.info("mq生产者启动成功");
        return mqProducer;
    }

}
