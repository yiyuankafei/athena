package com.athena.thirdpart.qiniu.application.util;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.athena.thirdpart.qiniu.application.config.QiniuConfig;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Client;
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

		String newUrl = config.getDic() + md5 + url.substring(url.lastIndexOf("."));

		Map<String, String> map = new HashMap<>();
		map.put("url", url);
		map.put("bucket", config.getBucketName());
		map.put("key", newUrl);

		String param = JSON.toJSONString(map);
		byte[] bodyByte = param.getBytes();

		Auth auth = Auth.create(config.getAccessKey(), config.getSecretKey());
		String accessToken = (String) auth
				.authorizationV2(config.getFetchUrl(), "POST", bodyByte, "application/json")
				.get("Authorization");

		Client client = new Client();
		StringMap headers = new StringMap();
		headers.put("Authorization", accessToken);
		try {
			client.post(config.getFetchUrl(), bodyByte, headers, Client.JsonMime);
		} catch (QiniuException e) {
			e.printStackTrace();
			log.info("异步上传酒店图片失败：{}", url);
			return url;
		}
		return config.getDomain() + "/" + newUrl;
	}
	
	/**
	 * 
	 * 同步上传
	 */
	public String syncUpload(String url, String md5) {
		return null;
	}

}
