package com.application.controller;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class TestController {

    @Autowired
    RedissonClient redissonClient;

    @RequestMapping("/test")
    public String test(String name) {
        return "athena-data-redisson :" + name;
    }

    @RequestMapping("/lock")
    public String lock(String name) throws InterruptedException {
        String key = "test_redisson";
        RLock lock = redissonClient.getLock(key);
        try {
            lock.lock();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        System.out.println("do something");
        return "success";
    }

    private void dosomething(String key) {
        RLock lock = redissonClient.getLock(key);
        try {
            lock.tryLock(10, TimeUnit.SECONDS);
            Thread.sleep(10000l);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
