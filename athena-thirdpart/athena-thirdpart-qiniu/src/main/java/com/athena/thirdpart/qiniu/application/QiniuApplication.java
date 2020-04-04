package com.athena.thirdpart.qiniu.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QiniuApplication {
	
	public static void main(String[] args) {
        SpringApplication.run(QiniuApplication.class, args);
    }

	public void run(String... arg0) throws Exception {
		System.out.println("系统启动成功！");
	}

}
