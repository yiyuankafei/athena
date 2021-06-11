package com.athena.common.util;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * JDK1.7
 * https://mp.weixin.qq.com/s/gHPBwxoIGl6m4VVsctMFQA
 * 用来测试各种内存排查监测工具
 */
public class BLQTest {

    /*public static void main(String[] args) {
        CyclicBarrier c ;
        Semaphore s;
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        lock.lock();
        lock.unlock();
        CountDownLatch latch = new CountDownLatch(10);
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
    }*/

    static class A implements Runnable {

        ReentrantLock lock;

        public A(ReentrantLock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(3000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("子线程lock");
            lock.lock();
            System.out.println("子线程获取锁");
            System.out.println("子线程处理业务============");
            lock.unlock();
            System.out.println("子线程释放");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        A a = new A(lock);
        lock.lock();
        System.out.println("主线程锁定");
        System.out.println("准备开启子线程");
        new Thread(a).start();
        System.out.println("子线程已开启");
        int i = 0;
        while (true) {
            Thread.sleep(10000l);
            System.out.println("主线程循环：" + (i ++));
            if (i == 20) {
                System.out.println("主线程准备释放锁");
                lock.unlock();
            }
        }
    }

}
