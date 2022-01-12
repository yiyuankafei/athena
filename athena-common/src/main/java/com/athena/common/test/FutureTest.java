package com.athena.common.test;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class FutureTest {

    /**
     *
     * 在Callable接口的call方法中定义业务逻辑，该方法有返回值
     * 再定义一个FutureTask类，包装callable，它是Runnable的实现类
     * 然后通过Thread.start将FutureTask启动，FutureTask在run方法中调用了Callable的call方法，获取返回值
     * 执行成功后，通过CAS的方式修改FutureTask中任务的状态，标记为完成，存储返回值，并且唤醒阻塞的线程
     *
     * 阻塞的线程其实是一个链表，FutureTask中存储了这个链表的头结点，循环节点依次唤醒
     *
     * get方法：
     *      查看FutureTask里的任务执行状态，若为已经结束，返回之前存储的返回值，若未结束，添加到阻塞链表
     * 阻塞逻辑：
     *      添加到阻塞链表的方法是一个for循环，在FutureTask内部标记的任务状态已完成时才会退出，否则，就在循环中先后执行以下逻辑
     *          1. 创建新的Node
     *          2. 将Node.next设置为FutureTask的waiterNode，然后将当前Node设置到waiterNode中（也就是修改头节点）
     *          3. 判断是否有设置超时时间，若有则休眠剩余的超时时间（休眠时间到了之后，未执行完，再次循环就会超时了）
     *          4. 否则进入死休眠，等待唤醒
     *
     *
     */

    public static void main(String[] args) throws Exception {
        String result = "";
        MyCallable myCallable = new MyCallable();
        FutureTask futureTask = new FutureTask(myCallable);
        Thread thread = new Thread(futureTask);
        thread.start();

        result = (String)futureTask.get();
        System.out.println("==========result:" + result);
    }


    public static  class MyCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            Thread.sleep(10000);
            return "result from call";
        }
    }

}
