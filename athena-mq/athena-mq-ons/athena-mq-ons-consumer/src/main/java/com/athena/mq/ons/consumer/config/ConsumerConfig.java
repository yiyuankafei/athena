package com.athena.mq.ons.consumer.config;

import com.aliyun.openservices.ons.api.Consumer;
import com.aliyun.openservices.ons.api.MessageListener;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.bean.ConsumerBean;
import com.aliyun.openservices.ons.api.bean.ProducerBean;
import com.aliyun.openservices.ons.api.bean.Subscription;
import com.athena.mq.ons.consumer.linstener.TesListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class ConsumerConfig {

    @Resource
    private MqConfig mqConfig;

    @Resource
    TesListener tesListener;

    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public ConsumerBean mqConsumer() {
        return createMqConsumer();
    }

    public ConsumerBean createMqConsumer() {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.GROUP_ID, mqConfig.getGroupId());
        properties.put(PropertyKeyConst.AccessKey, mqConfig.getAccessKeyId());
        properties.put(PropertyKeyConst.SecretKey, mqConfig.getAccessKeySecret());
        properties.put(PropertyKeyConst.MessageModel, mqConfig.getModel());
        properties.put(PropertyKeyConst.NAMESRV_ADDR, mqConfig.getNameSrvAddr());

        ConsumerBean mqConsumer = new ConsumerBean();
        mqConsumer.setProperties(properties);

        /*Consumer consumer = ONSFactory.createConsumer(properties);
        consumer.subscribe("ATHENA_TEST_TOPIC", "TEST_TAG", tesListener);*/

        Map<Subscription, MessageListener> subscriptionTable = new HashMap<>();
        Subscription subscription = new Subscription();
        subscription.setTopic(mqConfig.getTopic());
        subscription.setExpression("TEST_TAG");
        subscriptionTable.put(subscription, tesListener);

        mqConsumer.setSubscriptionTable(subscriptionTable);
        System.out.println("===启动消费者");
        return mqConsumer;
    }

}
