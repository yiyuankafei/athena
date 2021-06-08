package com.athena.spring.context.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class A {

    private B b;

    public void setB(B b) {
        this.b = b;
    }

    public void printB() {
        System.out.println("A.printB：" + b);
    }

}
