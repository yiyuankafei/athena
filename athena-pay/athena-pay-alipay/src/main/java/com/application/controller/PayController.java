package com.application.controller;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.athena.common.response.ResEnv;

@Controller
public class PayController {
	
	/**
	 * 支付宝网关
	 */
	public static final String ALI_WEB = "https://openapi.alipay.com/gateway.do";
	
	/**
	 * APP_ID
	 */
	public static final String APP_ID = "2017052507339298";
	
	/**
	 * 应用私钥
	 */
    public static final String RSA2_PRI_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC+xJ+RNc8Z8/T4m4aEeTvT23lhPQL44YbcSZSWvWz0ZZjnw4Y/aCdANHyhpuH3NLepkrboVT6guZyd4g8NQKpX7BHEdXt5V1ZwXqh0ci0ive4iBOj8lpj+cxYIQYNOJkbVcR5TFgYxjkyGu7UWDnDq85LkJNEENUe+cs+UV259KVWManIH9EbnYtAxAd2VVD0THc4V1d6T6ARqOsNo9P8LVS6292cCQrx3rRXRor03fMRlmuwgNZyWWGqOeD0uh6aWBU+ecXJ8xVBRHlfp1Jj299IRgYNmATHBq0Rdwf/QwSewREoMYITAKXvhtDrh1hgLl/+0lHzN4JQO7zJPFUiBAgMBAAECggEBALfBflLJmixNqfKvHOwOO2rfRhQ8SNijpNwcpFxvKrxgAOF+nZoGIYjfBg5QSelthlx5TmaNdj4rjIkob4c8etkmNBchc0Z2snSbOXixYnjMqs1qHWLxTX4MLXTWZjyo8iI3TBaMaHrjryM5PD16u5oV9bB9Jjj/eZQh04H4lh1IPka8ViXifU1XBW/qW9VQs2A4e6bz9rkk+G5sk5W9LooIgXRCl4Ixx8fqA0Dhv7qE4L0KHha6BKI7wwqleioBYv+nnC/osX0jwv7B/n1nRmhYWY+dSyQIWW3FIu+JvQ/saO2LKlOC3afJ0u+8dNQFsdPDKWYWy+vyRD/zc1zd7eECgYEA+vilTn18q8UEgXdSlfrY0WzquYLSVDejot6QioVQZC7FTbHkPNIXZ/Ej1RkkfDehw5DU4HV0PQKf72qHeKVTgkmfXbHcujT7vxccyBDZMauq4suqA/fGuupFNjQ6UzllkZTaCwZBs6IdzBaam55rRpVXSZgwbCjE0NlqnVJWeHMCgYEAwpcqd8in7oLNDaakXS3AIUGEK3K8TqV5SZ89VrGt3bIAgOg//AD1/MuhYHg82SrreHg0dy0r/yRgxNKbMtdG+GZwR0fzSF0+IYS7d0JMRBiZQ1SK7GXgalvdgQU9p8Ap8lTzWMF8BJ/hd/RNzYYTTGD7p2CM/6qp00qS8Ltm4jsCgYB+hYFYu3xxX+ZwmkYBJC/QDFec5C3ClTYico9ttd2cU9PV4luMqeFw3ilRUdaJ5MXhQI+BBNVvuHYGja70DGy4HwN/iZ4cJX/QiwdQeOX123RP15rDW1NNgbMYq2XH9QSWSvNNYX8X3x+kdrTExS2RrNIPYPDwZfEq1KCh5ROArwKBgHN8YbDrfnb4XBgg8WV3lJJoHrfmhUmrvVrrTe0Y0FZFoTTgIbE/JL6eX60wTvSYG13JAWJFhdoLzf6h+4fl6fHcYgB7/C/TXt9nthamWzsC2lryXuzD3bdhxAkhgiD/1CVFM5NWaXMZLCgT7uTuEKAA4vnIPIYz1ExY8vI6VmonAoGAS2UZGNh4GYyoVR7zPZT8gglhKuQgy0q4jKFTO0nPgvq0s237JMsfho3gNbMwkGGPPSqYXhE+HjEhabe8J8DYBblDHo7+MSZVcFBPayI2+S/t03mY/Zq0ov7PMYjPM9Xs8iRwdtQSFxcemeK3/b7YO8nzC7RXqKJFcu7WuDAoprk=";

    /**
	 * 应用公钥
	 */
    public static final String RSA2_PUB_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvsSfkTXPGfP0+JuGhHk709t5YT0C+OGG3EmUlr1s9GWY58OGP2gnQDR8oabh9zS3qZK26FU+oLmcneIPDUCqV+wRxHV7eVdWcF6odHItIr3uIgTo/JaY/nMWCEGDTiZG1XEeUxYGMY5Mhru1Fg5w6vOS5CTRBDVHvnLPlFdufSlVjGpyB/RG52LQMQHdlVQ9Ex3OFdXek+gEajrDaPT/C1UutvdnAkK8d60V0aK9N3zEZZrsIDWcllhqjng9LoemlgVPnnFyfMVQUR5X6dSY9vfSEYGDZgExwatEXcH/0MEnsERKDGCEwCl74bQ64dYYC5f/tJR8zeCUDu8yTxVIgQIDAQAB";
	
    /**
	 * 数据格式
	 */
	public static final String DATA_FORMAT = "json";
	
	/**
	 * 编码
	 */
	public static final String ENCODING = "UTF-8";
	
	/**
	 * 支付宝公钥
	 */
	public static final String ALI_PUB_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAopjaecegk7uIcfh3xfi1gpZayVN+o8v4F3UZJ3ESoqU4vH2rFOkD+RHC0RFnDt2ibtpMuqms7B8nipWMZeWGv4/oZg3hf7EJowV7v1IdPVLWBkjaAkcPUHJVxnj+kVbfLG4hUtwAwnf5iov1xsbtdoAsdfGXHed2gkD7sqVAOW+bGWRCAkRRcyiB59fSM/4DvnQGxcPC7IVqv7rFlyP+mGNhDQMRO5JiR+U9hYUHtc/vJnKHiGfLHIIq607y5WgUzTWEphNZV6DTk8MyCdjRYVvVBnjjeU0+eBVCP6381V0R0FbeMlZ5L9FgMLZbbzuRfQnfLj4+6CuazLnJhT4xEQIDAQAB";

	/**
	 * 加密方式
	 */
	public static final String SIGN_TYPE = "RSA2";
	
	/**
	 * 
	 * 开发者网关验证服务
	 */
    public static final String ALIPAY_CHECK_SERVICE = "alipay.service.check";
    
    /**
	 * 
	 * 同步通知地址
	 */
    public static final String RETURN_URL = "http://localhost:8999/returnUrl";
    
    /**
	 * 
	 * 异步通知地址
	 */
    public static final String NOTIFY_URL = "http://localhost:8999/callback";
    
    
    
    /**
     * 
     * 统一下单
     */
    @RequestMapping("/pay")
    public void pay(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	//从request中获取请求支付的订单信息
    	String totalAmount = "";
    	String subject = "";
    	String body = "";
    	String orderNo = ""; //生成订单号
    	
    	//未支付订单入库
    	
    	AlipayClient client=new DefaultAlipayClient(ALI_WEB, APP_ID, RSA2_PRI_KEY, DATA_FORMAT, ENCODING, ALI_PUB_KEY, SIGN_TYPE);
    	String form = ""; // 生成表单
    	
    	String clientType = request.getHeader("User-Agent");
    	//Androw  iphone windows-phone  移动端
    	if (clientType.indexOf("xxx") > 1) {
    		//移动端支付请求
    		AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
    		alipayRequest.setReturnUrl(RETURN_URL);
    		alipayRequest.setNotifyUrl(NOTIFY_URL);
			AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
			model.setProductCode("FAST_INSTANT_TRADE_PAY"); // 设置销售产品码
			model.setOutTradeNo(orderNo); // 设置订单号
			model.setSubject(subject); // 订单名称
			model.setTotalAmount(totalAmount); // 支付总金额
			model.setBody(body); // 设置商品描述
			alipayRequest.setBizModel(model);
			form = client.pageExecute(alipayRequest).getBody(); // 生成表单
    	} else {
    		// WEB端支付请求
    		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
    		alipayRequest.setReturnUrl(RETURN_URL);
    		alipayRequest.setNotifyUrl(NOTIFY_URL);
    		AlipayTradePayModel model = new AlipayTradePayModel();
    		model.setProductCode("FAST_INSTANT_TRADE_PAY"); // 设置销售产品码   即时到账
    		model.setOutTradeNo(orderNo); // 设置订单号
    		model.setSubject(subject); // 订单名称
    		model.setTotalAmount(totalAmount); // 支付总金额
    		model.setBody(body); // 设置商品描述
    		alipayRequest.setBizModel(model);
    		form = client.pageExecute(alipayRequest).getBody(); // 生成表单
    	}
		
		response.setContentType("text/html;charset=" + ENCODING); 
		response.getWriter().write(form); // 直接将完整的表单html输出到页面 
		response.getWriter().flush(); 
		response.getWriter().close();
    	
    }
    
    
    /**
     * 
     * 异步通知
     */
    @RequestMapping("/callback")
    public void callback(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	//获取支付宝GET过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map<String,String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
			System.out.println("name:" + name + ",valueStr:" + valueStr);
		}
		
		boolean signVerified = AlipaySignature.rsaCheckV1(params, ALI_PUB_KEY, ENCODING, SIGN_TYPE); //调用SDK验证签名
		//商户订单号
		String out_trade_no = request.getParameter("out_trade_no");
		//交易状态
		String trade_status = request.getParameter("trade_status");
		
		if(signVerified){ // 验证成功 更新订单信息
			if(trade_status.equals("TRADE_FINISHED")){
				
			}
			if(trade_status.equals("TRADE_SUCCESS")){
				
			}
			if(out_trade_no != null && out_trade_no.trim().length() > 0){
				
			}
		}else{
			
		}
    	
    }
    
    /**
     * 
     * 同步跳转
     */
    @RequestMapping("/returnUrl")
    public ModelAndView returnUrl(HttpServletRequest request, HttpServletResponse response) throws Exception {

    	ModelAndView mav = new ModelAndView();
		mav.addObject("title", "同步通知地址XXX");
		//获取支付宝GET过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map<String,String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
			System.out.println("name:" + name + ",valueStr:" + valueStr);
		}
		
		boolean signVerified = AlipaySignature.rsaCheckV1(params, ALI_PUB_KEY, ENCODING, SIGN_TYPE); //调用SDK验证签名

		if(signVerified) {
			mav.addObject("message", "支付成功 XXXXXX");
		}else {
			mav.addObject("message", "验签失败");
		}
		mav.setViewName("returnUrl");
		return mav;
    }
}
