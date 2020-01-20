package com.athena.thirdpart.oss.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OssApplication {
	
	public static void main(String[] args) {
        SpringApplication.run(OssApplication.class, args);
    }

	public void run(String... arg0) throws Exception {
		System.out.println("系统启动成功！");
	}

}
