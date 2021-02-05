package com.athena.common.util;

import lombok.Data;

@Data
public class SnowConfig {

    /**
     * 时间戳位数
     */
    public static Integer timeBits = 41;

    /**
     * 递增序列位数
     */
    public static Integer sequeueBits = 12;

    /**
     * 机器码位数
     */
    public static Integer machineBits = 5;

    /**
     * 机房码位数
     */
    public static Integer centerBits = 5;
}
