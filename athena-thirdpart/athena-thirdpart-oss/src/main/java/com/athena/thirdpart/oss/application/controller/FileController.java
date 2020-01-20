package com.athena.thirdpart.oss.application.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;
import com.athena.thirdpart.oss.application.config.OssConfig;
import com.athena.thirdpart.oss.application.util.OssUtil;

@RestController
@RequestMapping("/file")
public class FileController {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	OssConfig ossConfig;
	
	@RequestMapping("/upload")
	public String upload(MultipartFile file) throws Exception {
		OSSClient client = OssUtil.getInstance(ossConfig);
		String webPath = "";
		try {
			String originalName = file.getOriginalFilename();
			String path = sdf.format(new Date()) + "/" + UUID.randomUUID() + 
																originalName.substring(originalName.lastIndexOf("."));
			webPath = OssUtil.upload(client, path, file.getBytes(), ossConfig.getBucketName());
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			client.shutdown();
		}
		return webPath;
	}

}
