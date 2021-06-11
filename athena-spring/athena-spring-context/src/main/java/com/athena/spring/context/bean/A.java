package com.athena.spring.context.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class A {

    private B b;

    public void setB(B b) {
        this.b = b;
    }

    /**
     * 若提供有参构造方法，即时b上没有加@Autowired，并且A类的beanDefinition里autowiredMode=0，不装配，依然会注入B
     * 走的是自动装配-构造器模式的逻辑
     */
    /*public A(B b) {
        this.b = b;
    }*/

    public void printB() {
        System.out.println("A.printB：" + b);
    }

}
