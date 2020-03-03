package com.application.annotation.param.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Slf4j
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	log.info("进入拦截器");
        if (handler.getClass().equals(HandlerMethod.class)) {
            HandlerMethod method = (HandlerMethod) handler;
            Auth auth = method.getMethodAnnotation(Auth.class);
            log.info("注解{}", auth);
            if (auth != null) {
            	log.info("校验权限");
            	//权限校验
            }
        }
        return true;
    }
}