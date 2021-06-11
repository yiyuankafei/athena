package athena.spring.boot.application;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Application extends SpringBootServletInitializer implements CommandLineRunner {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("启动成功");
    }
}
