package com.athena.thirdpart.qiniu.application.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.common.util.HttpMethod;
import com.athena.thirdpart.qiniu.application.config.QiniuConfig;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Client;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

@Component
@Slf4j
public class QiniuUtil {
	
	@Autowired
	QiniuConfig config;
	
	/**
	 * 
	 * 异步上传
	 */
	public String asyncUpload(String url, String md5) {

		String newUrl = config.getQiniuConfig().getDic() + md5 + url.substring(url.lastIndexOf("."));

		Map<String, String> map = new HashMap<>();
		map.put("url", url);
		map.put("bucket", config.getQiniuConfig().getBucketName());
		map.put("key", newUrl);

		String param = JSON.toJSONString(map);
		byte[] bodyByte = param.getBytes();

		Auth auth = Auth.create(config.getQiniuConfig().getAccessKey(), config.getQiniuConfig().getSecretKey());
		String accessToken = (String) auth
				.authorizationV2(config.getQiniuConfig().getFetchUrl(), "POST", bodyByte, "application/json")
				.get("Authorization");

		Client client = new Client();
		StringMap headers = new StringMap();
		headers.put("Authorization", accessToken);
		try {
			client.post(config.getQiniuConfig().getFetchUrl(), bodyByte, headers, Client.JsonMime);
		} catch (QiniuException e) {
			e.printStackTrace();
			log.info("异步上传酒店图片失败：{}", url);
			return url;
		}
		return config.getQiniuConfig().getDomain() + "/" + newUrl;
	}
	
	/**
	 * 
	 * 同步上传
	 * @throws Exception 
	 */
	public String syncUpload(String url, String md5) {
		
		try {
			byte[] data = null;
			URL resourceUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection)resourceUrl.openConnection();
            conn.setRequestMethod(HttpMethod.GET);
			InputStream inputStream = conn.getInputStream();
			
			
			log.info("可读大小：{}", inputStream.available());
			
			byte[] buffer = new byte[1024];  
	        int len = 0;  
	        ByteArrayOutputStream bos = new ByteArrayOutputStream();  
	            
	        while((len = inputStream.read(buffer)) != -1) {  
	            bos.write(buffer, 0, len);  
	        }  
	        data = bos.toByteArray();  
	        bos.close();
	        log.info("文件大小：{}", data.length);
			
			
			Configuration cfg = new Configuration(Zone.zone2());
			UploadManager uploadManager = new UploadManager(cfg);
			
			Auth auth = Auth.create(config.getQiniuConfig().getAccessKey(), config.getQiniuConfig().getSecretKey());
			String upToken = auth.uploadToken(config.getQiniuConfig().getBucketName());
			
			String fileName = config.getQiniuConfig().getDic() + md5 + System.currentTimeMillis() + url.substring(url.lastIndexOf("."));
			Response response = uploadManager.put(data, fileName, upToken);
			if (response.isOK()) {
				return config.getQiniuConfig().getDomain() + "/" + fileName;
			} else {
				log.info("同步上传酒店图片失败：{},七牛云返回状态码:{}", url, response.statusCode);
				return url;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("同步上传酒店图片失败：{}", url);
			return url;
		}
		
	}

}
