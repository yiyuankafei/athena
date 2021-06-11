package athena.springboot.starter.marian.config;

import athena.springboot.starter.marian.po.PlatformPo;
import athena.springboot.starter.marian.service.PlatformService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnClass(PlatformPo.class)
public class AutoConfig {

    @Bean
    @ConditionalOnMissingBean(PlatformService.class)
    public PlatformService a() {
        PlatformService service = new PlatformService();
        return service;
    }

}
