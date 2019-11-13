package com.athena.thirdpart.message.application.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.athena.thirdpart.message.application.entity.Tel;
import com.athena.thirdpart.message.application.request.CreateTemplateRequest;
import com.athena.thirdpart.message.application.request.SendRequest;
import com.athena.thirdpart.message.application.util.HttpUtil;
import com.athena.thirdpart.message.application.util.JsonUtil;
import com.athena.thirdpart.message.application.util.Sha256;

@RestController
@RequestMapping("/message")
public class MessageController {
	
	@Value("${sdk_appid}")
	private String sdk_appid;
	
	@Value("${app_key}")
	private String app_key;
	
	@RequestMapping("/template/create")
	public String templateCreate(){
		
		CreateTemplateRequest request = new CreateTemplateRequest();
		request.setRemark("用户登录验证码");
		request.setText("登录验证码为:" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		Long time = LocalDateTime.now().toEpochSecond(ZoneOffset.ofHours(8));
		request.setTime(time);
		request.setTitle("登录验证码");
		request.setSig(Sha256.getSHA256("appkey=" + app_key + "&random=123456&time=" + time));
		String url = "https://yun.tim.qq.com/v5/tlssmssvr/add_template?sdkappid=" + sdk_appid + "&random=123456";
		String response = HttpUtil.post(url, JsonUtil.beanToJson(request));
		return response;
	}
	
	
	@RequestMapping("/send")
	public String send(){
		
		SendRequest request = new SendRequest();
		List<String> params = new ArrayList<>();
		params.add("1234567abcde");
		request.setParams(params);
		Long time = LocalDateTime.now().toEpochSecond(ZoneOffset.ofHours(8));
		request.setSig(Sha256.getSHA256("appkey=" + app_key + "&random=123456&time=" + time + "&mobile=13362139695"));
		Tel tel = new Tel();
		tel.setMobile("13362139695");
		tel.setNationcode("86");
		request.setTel(tel);
		request.setTime(time);
		request.setTpl_id(334588l);
		
		String url = "https://yun.tim.qq.com/v5/tlssmssvr/sendsms?sdkappid=" + sdk_appid + "&random=123456";
		String response = HttpUtil.post(url, JsonUtil.beanToJson(request));
		return response;
	}
}
