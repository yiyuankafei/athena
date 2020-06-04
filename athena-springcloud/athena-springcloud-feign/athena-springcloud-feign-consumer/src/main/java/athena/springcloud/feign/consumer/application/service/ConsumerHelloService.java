package athena.springcloud.feign.consumer.application.service;

import org.springframework.cloud.openfeign.FeignClient;

import com.athena.springcloud.feign.service.HelloInterface;

@FeignClient("athena-springcloud-feign-provider")
public interface ConsumerHelloService extends HelloInterface {

}
