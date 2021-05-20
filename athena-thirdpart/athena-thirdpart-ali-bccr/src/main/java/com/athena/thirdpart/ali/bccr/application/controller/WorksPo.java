package com.athena.thirdpart.ali.bccr.application.controller;

import lombok.Data;

@Data
public class WorksPo extends YoukuComparable {

    private Long id;

    private String thirdId;

    private String worksName;

    private String author;

    private String customerId;

    @Override
    public String getYoukuHash() {
        return worksName + author;
    }

    @Override
    public String getYoukuUniqueKey() {
        return thirdId;
    }
}
