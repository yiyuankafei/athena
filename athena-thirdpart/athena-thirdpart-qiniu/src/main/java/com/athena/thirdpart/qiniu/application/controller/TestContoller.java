package com.athena.thirdpart.qiniu.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.athena.thirdpart.qiniu.application.util.QiniuUtil;

@RestController
public class TestContoller {
	
	@Autowired
	QiniuUtil util;
	
	@RequestMapping("/async")
	public String async() {
		String s = "http://file.wisetravel.cn/api_kusadasi/44d2c82c7207060fd3a307b81a855812c651a031.jpeg";
		String asyncUpload = util.asyncUpload(s, DigestUtils.md5DigestAsHex(s.getBytes()));
		return asyncUpload;
	}
	
	@RequestMapping("/sync")
	public String sync() {
		String s = "http://file.wisetravel.cn/api_kusadasi/44d2c82c7207060fd3a307b81a855812c651a031.jpeg";
		//String s = "http://tiebapic.baidu.com/forum/pic/item/ab64034f78f0f73627bb20641d55b319ebc41309.jpg";
		String asyncUpload = util.syncUpload(s, DigestUtils.md5DigestAsHex(s.getBytes()));
		return asyncUpload;
	}

}
