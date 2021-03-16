package com.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAspect {

    @Pointcut("execution(* com.service.impl.TestServiceImpl.sleep())")
    private void sleepPointCut(){}

    @Around("sleepPointCut()")
    public void handlerSleep(ProceedingJoinPoint point) throws Throwable  {
        System.out.println("before sleep, close the light");
        point.proceed();
        System.out.println("after sleep, close the clock");

    }
}
