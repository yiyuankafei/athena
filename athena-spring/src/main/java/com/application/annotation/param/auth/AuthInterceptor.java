package com.application.annotation.param.auth;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        if (handler.getClass().equals(HandlerMethod.class)) {
            HandlerMethod method = (HandlerMethod) handler;
            Auth auth = method.getMethodAnnotation(Auth.class);
            if (auth != null) {
            	//权限校验
            }
        }
        return true;
    }
}