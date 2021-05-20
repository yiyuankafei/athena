package com.athena.thirdpart.ali.bccr.application.controller;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Works extends YoukuComparable {

    private String worksId;

    private String name;

    private String author;

    private BigDecimal price;

    private Date createTime;

    @Override
    public String getYoukuHash() {
        return name + author;
    }

    @Override
    public String getYoukuUniqueKey() {
        return worksId;
    }
}
