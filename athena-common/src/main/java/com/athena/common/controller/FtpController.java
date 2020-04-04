package com.athena.common.controller;

import java.io.File;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.athena.common.config.FtpConfig;
import com.athena.common.config.FtpConfig.Ftp;
import com.athena.common.util.FtpUtil;
import com.athena.common.util.ZipUtil;

@RestController
@Slf4j
public class FtpController {
	
	@Autowired
	FtpConfig config;
	
	@RequestMapping("/ftp")
	public void ftp() throws Exception {
		
		log.info("开始导入静态数据");
		Ftp ftp = config.getFtp();
		//下载静态数据文件
		FtpUtil.downloadFile(ftp);
		
		//解压
		String zipFileName = ftp.getLocalFileName();
		String targetFileName = zipFileName.substring(0, zipFileName.lastIndexOf("."));
		ZipUtil.unzipFile(new File(zipFileName), targetFileName);
		
		log.info("导入静态数据结束");
		
	}

}
