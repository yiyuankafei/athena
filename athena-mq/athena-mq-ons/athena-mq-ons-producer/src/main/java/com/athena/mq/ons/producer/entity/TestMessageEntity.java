package com.athena.mq.ons.producer.entity;

import lombok.Data;

import java.util.Date;

@Data
public class TestMessageEntity {

    private String name;

    private Integer age;

    private Date registerTime;

}
