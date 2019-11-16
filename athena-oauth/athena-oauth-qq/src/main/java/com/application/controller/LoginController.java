package com.application.controller;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.athena.common.util.HttpClientUtil;

@Controller
public class LoginController {
	
	private static String appId = "";
	
	private static String appSecret = "";
	
	private static String redirectUrl = "http://xxx/login/callback";
	
	private static String OAuthUrl = "https://graph.qq.com/oauth2.0/authorize";
	
	private static String tokenUrl = "https://graph.qq.com/oauth2.0/token";
	
	private static String openIdUrl = "https://graph.qq.com/oauth2.0/me";
	
	private static String userInfoUrl = "https://graph.qq.com/user/get_user_info";
	
	@RequestMapping("/login/QQLogin")
	public String qqLogin() throws Exception {
		
		return "redirect:" + OAuthUrl + "?response_type=code&client_id=" + appId +
														"&redirect_uri=" + URLEncoder.encode(redirectUrl, "UTF-8") +
														"&state=" + 123456 + //随机生成，防止CSRF攻击
														"&scope=get_user_info";
														
	}
	
	@ResponseBody
	@RequestMapping("/login/callback")
	public void callback(HttpServletRequest request) throws Exception {
		
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		
		//通过code获取accessToken
		String url = tokenUrl + "?grant_type=authorization_code" + 
										"&client_id=" + appId +
										"&client_secret=" + appSecret + 
										"&code=" + code + 
										"redirect_uri=" +  URLEncoder.encode(redirectUrl, "UTF-8");
		
		String accessTokenResponse = HttpClientUtil.doGet(url);
		String accessToken = ""; //从accessTokenResponse中获取AccessToken
		
		//调用openId接口，获取openId
		url = openIdUrl + "?access_token=" + accessToken;
		String openIdResponse = HttpClientUtil.doGet(url);
		String openId = ""; //从openIdResponse中获取openId
		String oauthConsumerKey = ""; //从openIdResponse中获取oppId
		
		//调用getUserInfo接口，获取用户信息
		url = userInfoUrl + "?access_token=" + accessToken + 
								"&oauth_consumer_key=" + oauthConsumerKey;
		String userResponse = HttpClientUtil.doGet(url);
		//解析用户信息
	}
	

}
