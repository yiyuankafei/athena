package com.athena.socket.netty.server.application;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class NettyServerApplication extends SpringBootServletInitializer implements CommandLineRunner {
	
	public static void main(String[] args) {
        SpringApplication.run(NettyServerApplication.class, args);
    }

	public void run(String... arg0) throws Exception {
		System.out.println("系统启动成功！");
	}
}
