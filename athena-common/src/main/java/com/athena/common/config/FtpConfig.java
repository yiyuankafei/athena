package com.athena.common.config;

import lombok.Getter;
import lombok.Setter;

import org.springframework.context.annotation.Configuration;

import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;

@Getter
@Setter
@Configuration
@NacosConfigurationProperties(prefix = "hotel", dataId = "haoqiao-hotel-config", type = ConfigType.YAML, autoRefreshed = true)
public class FtpConfig {
	
	/** FTP配置 */
	private Ftp ftp;
	
	
	@Getter
	@Setter
	public static class Ftp {
		
		/** FTP地址 */
		private String url;
		
		/** FTP端口 */
		private Integer port;
		
		/** FTP用户名 */
		private String user;
		
		/** FTP用户密码 */
		private String password;
		
		/** FTP编码 */
		private String charset;
		
		/** 静态数据文件名 */
		private String targetFileName;
		
		/** 本地文件名 */
		private String localFileName;
		
		/** 静态数据-国家文件名 */
		private String countryFile;
		
		/** 静态数据-城市文件名 */
		private String cityFile;
		
		/** 静态数据-酒店文件名 */
		private String hotelFile;
		
		/** 静态数据-房型文件名 */
		private String roomFile;
	}

}
