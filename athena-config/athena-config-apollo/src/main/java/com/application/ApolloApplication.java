package com.application;

import com.alibaba.fastjson.JSON;
import com.application.config.ConfigLoad;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan({"com.application.mapper","com.application.mapper.custom"})
@EnableApolloConfig
@Slf4j
public class ApolloApplication  extends SpringBootServletInitializer implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ApolloApplication.class);
        springApplication.setDefaultProperties(ConfigLoad.load(ApolloApplication.class));
        springApplication.run(args);
    }

    public void run(String... args) {
        log.info("启动成功，当前环境：{}", ConfigLoad.getCurrentEnv());
        log.info("启动配置：{}", JSON.toJSONString(ConfigLoad.load(ApolloApplication.class)));
    }

}
