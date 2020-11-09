package com.athena.thirdpart.bos.application.util;


import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.baidubce.services.bos.model.PutObjectResponse;

import java.io.File;
import java.io.InputStream;

public class BosUtil {

    /**
     * 获取BosClient对象
     *
     * @param accessKeyId     ak
     * @param secretAccessKey sk
     * @param endpoint        根节点
     */
    public static BosClient getBosClient(String accessKeyId, String secretAccessKey, String endpoint) {
        BosClientConfiguration config = new BosClientConfiguration();
        config.setMaxConnections(10);
        config.setCredentials(new DefaultBceCredentials(accessKeyId, secretAccessKey));
        config.setEndpoint(endpoint);
        return new BosClient(config);
    }

    /**
     * 百度bos以file形式上传文件（不超过5GB）
     *
     * @param client     BosClient链接对象
     * @param file       要上传的文件
     * @param bucketName 上传到那个文件夹（newsurvey下的文件夹，如果没有会自动创建，不能用“/” 创建多层）
     * @param objectKey  文件路径/文件名（可以用“/”来创建多层文件夹）
     * @return 上传成功后的tag
     */
    public static PutObjectResponse uploadFileToBos(BosClient client, File file,
                                                    String bucketName, String objectKey) {
        return client.putObject(bucketName, objectKey, file);
    }

    /**
     * 以数据流形式上传Object（不超过5GB）
     *
     * @param client      BosClient链接对象
     * @param inputStream 要上传的数据流    InputStream inputStream = new FileInputStream("/path/test.zip");
     * @param bucketName  上传到那个文件夹（newsurvey下的文件夹，如果没有会自动创建，不能用“/” 创建多层）
     * @param objectKey   文件路径/文件名（可以用“/”来创建多层文件夹）
     * @return 上传成功后的tag
     */
    public static PutObjectResponse uploadInputStreamToBos(BosClient client, InputStream inputStream,
                                                           String bucketName, String objectKey) {
        return client.putObject(bucketName, objectKey, inputStream);
    }

    /**
     * 以二进制串上传Object（不超过5GB）
     *
     * @param client     BosClient链接对象
     * @param file       要上传的byte
     * @param bucketName 上传到那个文件夹（newsurvey下的文件夹，如果没有会自动创建，不能用“/” 创建多层）
     * @param objectKey  文件路径/文件名（可以用“/”来创建多层文件夹）
     * @return 上传成功后的tag
     */
    public static PutObjectResponse uploadByteToBos(BosClient client, byte[] file,
                                                    String bucketName, String objectKey) {
        return client.putObject(bucketName, objectKey, file);
    }

}
