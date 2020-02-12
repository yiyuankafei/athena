package com.application.job.xxl.application.job;


import org.springframework.stereotype.Component;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;

@Component
public class FirstJob {
	
	@XxlJob("firstJobHandler")
    public ReturnT<String> firstJobHandler(String param) throws Exception {
        XxlJobLogger.log("firstJobHandler---------------------");
        System.out.println("firstJobHandler------");
        return ReturnT.SUCCESS;
    }

}
