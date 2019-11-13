package com.athena.thirdpart.message.application.util;

import lombok.extern.slf4j.Slf4j;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

@Slf4j
public class HttpUtil {
	
	public static String post(String url, String entity) {
		
		log.info("请求数据:{}", entity);
		
		CloseableHttpClient client = HttpClients.createDefault();
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		HttpPost post = new HttpPost(url);
		
		StringEntity requestEntity = new StringEntity(entity,"UTF-8");
		post.setHeader("Content-type", "application/json");
		post.setEntity(requestEntity);
		
		String response = null;
		try {
			response = client.execute(post,responseHandler);
			client.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("网络连接异常，发送失败");
		}
		
		log.info("响应数据:{}", response);
		return response;
	}
}
