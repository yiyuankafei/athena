package com.application;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;


@SpringBootApplication
@NacosPropertySource(dataId = "example", autoRefreshed = true)
public class Application extends SpringBootServletInitializer implements CommandLineRunner {
	
	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

	public void run(String... arg0) throws Exception {
		System.out.println("系统启动成功！");
	}
}
