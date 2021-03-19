package com.athena.common.util;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * JDK1.7
 * https://mp.weixin.qq.com/s/gHPBwxoIGl6m4VVsctMFQA
 * 用来测试各种内存排查监测工具
 */
public class BLQTest {

    public static void main(String[] args) {
        ConcurrentLinkedQueue<Object> queue = new ConcurrentLinkedQueue<>();
        queue.add(new Object());
        Object object = new Object();
        int loops = 0;
        Runtime rt = Runtime.getRuntime();
        Long last = System.currentTimeMillis();

        while (true) {
            if (loops % 10000 == 0) {
                long now = System.currentTimeMillis();
                long duration = now - last;
                last = now;
                System.err.printf("duration=%d,q.size=%d,memory max = %d  free=%d  total=%d%n",
                        duration, queue.size(), rt.maxMemory(), rt.freeMemory(), rt.totalMemory());
            }
            queue.add(object);
            queue.remove(object);
            ++loops;
        }
    }

}
