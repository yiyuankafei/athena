package com.athena.thirdpart.bos.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BosApplication {

    public static void main(String[] args) {
        SpringApplication.run(BosApplication.class, args);
    }

    public void run(String... arg0) throws Exception {
        System.out.println("系统启动成功！");
    }
}
