package com.application;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;


@SpringBootApplication
@NacosPropertySource(dataId = "example", autoRefreshed = true)
public class Application extends SpringBootServletInitializer implements CommandLineRunner {
	
	@NacosInjected // 使用nacos client的NacosInjected注解注入服务
	private NamingService namingService;
	
	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

	public void run(String... arg0) throws Exception {
		System.out.println("系统启动成功！");
        namingService.registerInstance("generic-test", "47.92.209.37", 8848); // 注册中心的地址
	}
}
