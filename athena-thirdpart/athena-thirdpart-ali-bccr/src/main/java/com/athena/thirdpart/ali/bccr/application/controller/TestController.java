package com.athena.thirdpart.ali.bccr.application.controller;

import cn.com.antcloud.api.AntFinTechApiClient;
import cn.com.antcloud.api.AntFinTechProfile;
import cn.com.antcloud.api.bccr.v1_0_0.request.CreateRecodescreenRequest;
import cn.com.antcloud.api.bccr.v1_0_0.request.GetUploadurlRequest;
import cn.com.antcloud.api.bccr.v1_0_0.request.QueryRecodescreenRequest;
import cn.com.antcloud.api.bccr.v1_0_0.response.CreateRecodescreenResponse;
import cn.com.antcloud.api.bccr.v1_0_0.response.GetUploadurlResponse;
import cn.com.antcloud.api.bccr.v1_0_0.response.QueryRecodescreenResponse;
import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/test")
public class TestController {

    private static final String END_POINT = "https://prodapigw.cloud.alipay.com";

    private static final String ACCESS_ID = "111";

    private static final String ACCESS_SECRET = "111";

    private static final String RECORD_SCREEN_AREA = "cn-shanghai";

    private static final String CALLBACK_URL = "http://1yuankafei.natapp1.cc/test/callback";

    private static final String CERT_TYPE = "IDENTITY_CARD";

    private static final String RECORD_SCREEN_TYPE = "WINDOWS";

    private static final Long WAIT_TIME = 10l;

    private static final Long MAX_TIME = 300l;

    @Resource
    private RedisTemplate redisTemplate;

    @RequestMapping("/hello")
    public String hello(String name) {
        return "hello-bccr:" + name;
    }

    @RequestMapping("/demo")
    public String demo() throws Exception {
        AntFinTechProfile profile = AntFinTechProfile.getProfile(END_POINT, ACCESS_ID, ACCESS_SECRET);
        AntFinTechApiClient client = new AntFinTechApiClient(profile);
        GetUploadurlRequest request = new GetUploadurlRequest();
        request.setFileName("test.jpg");
        request.setProductInstanceId("111");
        GetUploadurlResponse response = client.execute(request);
        if (response.isSuccess()) {
            System.out.println(response.getUrl());
        } else {
            System.out.println(response.getResultCode());
        }
        return "success";
    }

    @RequestMapping("/create")
    public String create() throws Exception {
        AntFinTechProfile profile = AntFinTechProfile.getProfile(END_POINT, ACCESS_ID, ACCESS_SECRET);
        AntFinTechApiClient client = new AntFinTechApiClient(profile);

        CreateRecodescreenRequest request = new CreateRecodescreenRequest();
        request.setArea(RECORD_SCREEN_AREA);
        request.setCallbackUrl(CALLBACK_URL);
        request.setCertName("张三丰");
        request.setCertNo("110101199003076675");
        request.setCertType("IDENTITY_CARD");
        request.setClientToken(System.currentTimeMillis() + "");
        request.setMaxTimeInMin(MAX_TIME);
        request.setName("测试取证：" + System.currentTimeMillis());
        request.setType("WINDOWS");
        request.setWaitInMin(WAIT_TIME);

        request.setProductInstanceId("BCCR-ONLINE");
        request.setRegionName("CN-HANGZHOU-FINANCE");

        // 发送请求，并且获取响应结果
        CreateRecodescreenResponse response = client.execute(request);

        System.out.println("*******************************");
        System.out.println(JSON.toJSONString(response));
        System.out.println("*******************************");
        return JSON.toJSONString(response);

    }

    @RequestMapping("/get")
    public String get(String id) throws Exception {
        AntFinTechProfile profile = AntFinTechProfile.getProfile(END_POINT, ACCESS_ID, ACCESS_SECRET);
        AntFinTechApiClient client = new AntFinTechApiClient(profile);

        QueryRecodescreenRequest request = new QueryRecodescreenRequest();
        request.setEvidenceId(id);
        request.setProductInstanceId("BCCR-ONLINE");
        request.setRegionName("CN-HANGZHOU-FINANCE");

        // 发送请求，并且获取响应结果
        QueryRecodescreenResponse response = client.execute(request);

        System.out.println("*******************************");
        System.out.println(JSON.toJSONString(response));
        System.out.println("*******************************");
        return JSON.toJSONString(response);

    }

    @RequestMapping("/callback")
    public String callback(String message) {
        System.out.println("=========回调=========");
        System.out.println(message);
        System.out.println("=========回调=========");
        return "success";
    }

    @RequestMapping("/redis")
    public String redis(String message) {
        redisTemplate.opsForValue().setIfAbsent("cccccccccccccccccccccccccccccccccc", "111111111111111111", 1, TimeUnit.MINUTES);
        return "success";
    }

    @RequestMapping("/redis2")
    public String redis2(String message) {
        redisTemplate.opsForValue().setIfAbsent("mis", "111111111111111111");
        return "success";
    }

}
