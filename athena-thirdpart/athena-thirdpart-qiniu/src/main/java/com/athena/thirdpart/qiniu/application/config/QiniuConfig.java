package com.athena.thirdpart.qiniu.application.config;

import lombok.Getter;
import lombok.Setter;

import org.springframework.context.annotation.Configuration;

import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;

@Getter
@Setter
@Configuration
@NacosConfigurationProperties(prefix = "hotel", dataId = "haoqiao-hotel-config", type = ConfigType.YAML, autoRefreshed = true)
public class QiniuConfig {
	
	/** 七牛云配置 */
	private Qiniu qiniuConfig;
	
	@Getter
	@Setter
	public static class Qiniu {
		
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

}
