package com.athena.thirdpart.qiniu.application.config;

import lombok.Getter;
import lombok.Setter;

import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class QiniuConfig {
	
	/** 图片前缀 */
	private String dic;
	
	/** 用户key */
	private String accessKey;
	
	/** 密钥 */
	private String secretKey;
	
	/** 存储空间 */
	private String bucketName;
	
	/** 异步抓取URL */
	private String fetchUrl;
	
	/** 访问域名 */
	private String domain;

}
