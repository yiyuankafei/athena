package com.application.controller;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.application.entity.AccessToken;
import com.application.entity.WechatUserUnionID;
import com.athena.common.util.HttpClientUtil;

@Controller
public class LoginController {
	
	private static String appId = "";
	
	private static String appSecret = "";
	
	private static String redirectUrl = "http://xxx/login/callback";
	
	private static String OAuthUrl = "https://open.weixin.qq.com/connect/qrconnect";
	
	private static String tokenUrl = "https://open.weixin.qq.com/sns/oauth2/access_token";
	
	private static String userInfoUrl = "https://open.weixin.qq.com/sns/userinfo";
	
	
	@RequestMapping("/login/weixinLogin")
	public String qqLogin() throws Exception {
		
		return "redirect:" + OAuthUrl + "?response_type=code&appid=" + appId +
														"&redirect_uri=" + URLEncoder.encode(redirectUrl, "UTF-8") +
														"&state=" + 123456 + //随机生成，防止CSRF攻击
														"&scope=snsapi_login" + 
														"&response_type=code";
														
	}
	
	@ResponseBody
	@RequestMapping("/login/callback")
	public void callback(HttpServletRequest request) throws Exception {
		
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		
		//校验state
		
		//通过code获取accessToken
		String url = tokenUrl + "?grant_type=authorization_code" + 
										"&appid=" + appId +
										"&secret=" + appSecret + 
										"&code=" + code;
		
		String accessTokenResponse = HttpClientUtil.doGet(url);
		JSONObject object1 = JSONObject.parseObject(accessTokenResponse);
		AccessToken accessToken = JSONObject.toJavaObject(object1, AccessToken.class);
		
		
		//调用getUserInfo接口，获取用户信息
		url = userInfoUrl + "?access_token=" + accessToken.getAccess_token() + 
								"&openid=" + accessToken.getOpenid();
		//解析用户信息
		String userResponse = HttpClientUtil.doGet(url);
		JSONObject object2 = JSONObject.parseObject(userResponse);
		WechatUserUnionID userInfo = JSONObject.toJavaObject(object2, WechatUserUnionID.class);
		System.out.println(userInfo.getNickname());
		System.out.println(userInfo.getOpenid());
		System.out.println(userInfo.getHeadimgurl());
	}
	
	/**
	 * 
	 *  js内嵌方式
	 *  js中引入http://res.wx.qq.com/connect/zh_CN/htmledition/js/wxLogin.js
	 *  
	 *  在需要使用的地方
	 *  var obj = new WxLogin({
	 *  	self_refirect:true,
	 *  	id:"login_container",
	 *  	app_id:"",
	 *  	scope:"",
	 *  	redirect_uri:"",
	 *  	state:"",
	 *  	style:"",
	 *  	href:""
	 *  });
	 * 
	 */

}
