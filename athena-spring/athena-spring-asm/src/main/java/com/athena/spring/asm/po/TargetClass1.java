package com.athena.spring.asm.po;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Component
@Order
@Service
@Repository
public class TargetClass1 {

    static {
        System.out.println("TargetClass1初始化：========================================");
    }
}
