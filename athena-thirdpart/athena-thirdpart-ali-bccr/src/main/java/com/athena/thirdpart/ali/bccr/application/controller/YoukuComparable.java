package com.athena.thirdpart.ali.bccr.application.controller;

import lombok.Data;

@Data
public abstract class YoukuComparable {

    private Integer count = 1;

    public abstract String getYoukuHash();

    public abstract String getYoukuUniqueKey();

}
