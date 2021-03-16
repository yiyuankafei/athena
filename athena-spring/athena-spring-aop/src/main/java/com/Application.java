package com;

import com.service.TestService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class Application extends SpringBootServletInitializer implements CommandLineRunner {

    public static void main(String[] args) {
        //SpringApplication.run(Application.class, args);
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        TestService testService = context.getBean(TestService.class);
        System.out.println("testService = " + testService.getClass());
    }

    public void run(String... arg0) throws Exception {
        System.out.println("系统启动成功！");
    }
}
