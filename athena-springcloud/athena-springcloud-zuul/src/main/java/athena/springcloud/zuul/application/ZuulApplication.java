package athena.springcloud.zuul.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import athena.springcloud.zuul.application.filter.AccessFilter;


@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class ZuulApplication {
	
	public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }
	
	@Bean
	public AccessFilter accessFilter() {
		return new AccessFilter();
	}

}
