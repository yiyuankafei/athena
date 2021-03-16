package com.athena.common.util;

import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;

public class SnowFlake {

    /**
     * 起始时间，可以是项目开始运行的时间
     */
    private static final Long START_TIME = System.currentTimeMillis();

    /**
     * 机房ID，可以从配置文件或者启动参数中读取
     */
    private static final Integer CENTER_ID = 3;

    /**
     * 机器ID，可以从配置文件或者启动参数中读取
     */
    private static final Integer MACHINE_ID = 1;

    /**
     * 当前毫秒的递增序列
     */
    private static Integer sequence = 0;

    /**
     * 上次获取ID的时间戳
     */
    private static Long lastTime = 0l;

    private static Integer timeOffset = SnowConfig.sequeueBits + SnowConfig.machineBits + SnowConfig.centerBits;

    private static Integer centerOffset = SnowConfig.sequeueBits + SnowConfig.machineBits;

    private static Integer machineOffset = SnowConfig.sequeueBits;

    private final static Integer SequenceBarrier = -1 ^ (-1 << SnowConfig.sequeueBits);

    private synchronized static Long nextId() {
        new ArrayList<>();
        Long currentTime = System.currentTimeMillis();
        if (currentTime > lastTime) {
            sequence = 0;
            lastTime = currentTime;
        }
        Integer seq = (sequence + 1) & SequenceBarrier;
        if (seq == 0) {
            try {
                Thread.sleep(1);
                currentTime = System.currentTimeMillis();
                sequence = 0;
                lastTime = currentTime;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return ((currentTime - START_TIME) << timeOffset)
                | (CENTER_ID << centerOffset)
                | (MACHINE_ID << machineOffset)
                | seq;
    }

    public static void main(String[] args) {
        while (true) {
            Long id = nextId();
            System.out.println(id);
            try {
                Thread.sleep(RandomUtils.nextInt(0,1000));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
