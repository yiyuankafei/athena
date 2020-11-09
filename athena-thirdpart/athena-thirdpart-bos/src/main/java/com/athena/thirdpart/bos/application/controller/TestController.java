package com.athena.thirdpart.bos.application.controller;

import com.alibaba.fastjson.JSON;
import com.athena.thirdpart.bos.application.config.BosConfig;
import com.athena.thirdpart.bos.application.util.BosUtil;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.model.PutObjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    BosConfig config;


    @RequestMapping("/t1")
    public String t1() {

        System.out.println(JSON.toJSONString(config));

        BosClient client = BosUtil.getBosClient(config.getAccessKey(), config.getSecretKey(), config.getEndpoint());
        File file = new File("C:\\Users\\Administrator\\Desktop\\IMG_4628.JPG");
        String obejctName = "/cert/file/xxxxx.png";
        PutObjectResponse response = BosUtil.uploadFileToBos(client, file, config.getBucketName(), obejctName);

        System.out.println(response.getETag());

        return "success";
    }

}
