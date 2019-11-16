package com.application.controller;

import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayOpenPublicGisQueryRequest;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipayOpenPublicGisQueryResponse;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import com.application.config.AlipayConfig;
import com.application.entity.User;

@RequestMapping("/alipay")
@Controller
public class LoginController {
	
	private static String appId = "";
	
	private static String redirectUrl = "http://220.191.160.243:7001/alipay/callback";
	
	/**
	 * 
	 * 支付宝三方登陆
	 */
	@RequestMapping("/login")
    public String login(ModelMap model){
		return "redirect:https://openauth.alipay.com/oauth2/publicAppAuthorize.htm?app_id=" + appId
											+ "&scope=auth_user&redirect_uri=" + redirectUrl;
    }
	
	/**
	 * 
	 * 支付宝登陆回调
	 */
	@RequestMapping("/callback")
    public String getUserInfo(HttpServletRequest request, HttpServletResponse response){
		//获取授权码
		String auth_code = request.getParameter("auth_code");
		AlipayClient authClient = getAlipayClient();
		AlipaySystemOauthTokenRequest authRequest = new AlipaySystemOauthTokenRequest();
		authRequest.setCode(auth_code);
		authRequest.setGrantType("authorization_code");
		
		AlipayUserInfoShareResponse userinfoShareResponse = null;
		try {
			//交换令牌
			AlipaySystemOauthTokenResponse authResponse = authClient.execute(authRequest);
			if (authResponse.isSuccess()) {
				String token = authResponse.getAccessToken();
				AlipayClient userInfoClient = getAlipayClient();
				AlipayUserInfoShareRequest requestUser = new AlipayUserInfoShareRequest();
				userinfoShareResponse = null;
				//获取用户信息
				userinfoShareResponse = userInfoClient.execute(requestUser,token);
			}
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		
		//用户信息入库并登陆
		User user = new User();
		user.setUserId(getString(userinfoShareResponse.getUserId())); //支付宝用户ID
		user.setAvatar(getString(userinfoShareResponse.getAvatar())); //用户头像地址
		user.setProvince(getString(userinfoShareResponse.getProvince())); //省份名称
		user.setCity(getString(userinfoShareResponse.getCity())); //城市名称
		user.setNickName(getString(userinfoShareResponse.getNickName())); //用户昵称
		user.setIsStudentCertified(getString(userinfoShareResponse.getIsStudentCertified())); //是否是学生
		user.setUserType(getString(userinfoShareResponse.getUserType())); //用户类型  1-公司账户  2-个人账户
		user.setUserStatus(getString(userinfoShareResponse.getUserStatus())); //用户状态  Q-快速注册用户  T-已认证用户  B-被冻结账户  W-已注册未激活
		user.setIsCertified(getString(userinfoShareResponse.getIsCertified())); //是否通过实名认证  T-通过  F-未通过
		user.setGender(getString(userinfoShareResponse.getGender())); //性别  F-女性  M-男性
		user.setLastUpdateTime(new Date());
		
		//获取用户当前经纬度信息
		AlipayClient userLocationClient = getAlipayClient();
		AlipayOpenPublicGisQueryRequest locationRequest = new AlipayOpenPublicGisQueryRequest();
		locationRequest.setBizContent("{\"userId\":\""+user.getUserId()+"\"}");
		try {
			AlipayOpenPublicGisQueryResponse locationresponse = userLocationClient.execute(locationRequest);
			if(locationresponse.isSuccess()){
				user.setLng(Double.parseDouble(locationresponse.getLongitude()));
				user.setLat(Double.parseDouble(locationresponse.getLatitude()));
			}
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		
		//未获取到登陆信息，默认值为0
		if (user.getLng() == null) {
			user.setLng(Double.parseDouble("0"));
			user.setLat(Double.parseDouble("0"));
		}
		//TODO 目前应用网关为http://39.108.237.55/ZZ_ali/gateway.do，需修改为http://xxx.xxx.xxx.xxx:xxxx/alipay/gateway
		//{"code":"40004","msg":"Business Failed","sub_code":"PUB.USER_GIS_NOT_EXISTS","sub_msg":"用户地理位置信息不存在"}
		System.out.println("===lng:"+user.getLng());
		System.out.println("===lat:"+user.getLat());
		
		//String lng = "119.763";
		//String lat = "29.1026";
		//用户登陆存入Session
		//user.setLng(new Double(lng));
		//user.setLat(new Double(lat));
		request.getSession().setAttribute("loginUser", user);
		if (request.getSession().getAttribute("redirectUrl") != null) {
			return "redirect:"+request.getSession().getAttribute("redirectUrl");
		} else {
			return "redirect:/store/list";
		}
    }
	
	/**
	 * 
	 * 创建请求客户端
	 */
	public AlipayClient getAlipayClient() {
		AlipayClient client = new  DefaultAlipayClient(AlipayConfig.ALI_WEB,AlipayConfig.APP_ID,AlipayConfig.RSA2_PRI_KEY,
														AlipayConfig.DATA_FORMAT,AlipayConfig.ENCODING,AlipayConfig.ALI_PUB_KEY,
														AlipayConfig.SIGN_TYPE);
		return client;
	}
	
	
	/**
	 * 
	 * 应用网关，响应支付宝请求
	 */
	@RequestMapping("/gateway")
	public void gateway(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//支付宝响应消息  
        String responseMsg = "";
        //解析请求参数
        Map<String, String> params = new HashMap<String, String>();
        if(null != request){
            Set<String> paramsKey = request.getParameterMap().keySet();
            for(String key : paramsKey){
                params.put(key, request.getParameter(key));
            }
        }
        //验证签名
    	if (!AlipaySignature.rsaCheckV2(params, AlipayConfig.ALI_PUB_KEY,
				"GBK",AlipayConfig.SIGN_TYPE)) {
			throw new AlipayApiException("验证签名异常！");
		}
    	//构造响应
    	StringBuilder builder = new StringBuilder();
    	//开发者验证，更换网关时返回指定信息
        if (params.get("service").equals(AlipayConfig.ALIPAY_CHECK_SERVICE)) {
        	
            builder.append("<success>").append(Boolean.TRUE.toString()).append("</success>");
            builder.append("<biz_content>").append(AlipayConfig.RSA2_PUB_KEY)
                .append("</biz_content>");
            responseMsg = builder.toString();
        } 
        //支付宝的其他请求，回复统一的信息
        else {
        	String fromUserId = "";
        	//解析请求中的userId
        	Pattern pattern = Pattern.compile("<FromUserId><\\!\\[CDATA\\[(\\d*)");
    		Matcher matcher = pattern.matcher(params.get("biz_content"));
    		while (matcher.find()) {
    			fromUserId = matcher.group(1);
    		}
        	builder.append("<XML>");
        	builder.append("<ToUserId><![CDATA[" + fromUserId + "]]></ToUserId>");
        	builder.append("<AppId><![CDATA[" + AlipayConfig.APP_ID + "]]></AppId>");
        	builder.append("<CreateTime>" + Calendar.getInstance().getTimeInMillis() + "</CreateTime>");
        	builder.append("<MsgType><![CDATA[ack]]></MsgType>");
        	builder.append("</XML>");
        	responseMsg = builder.toString();
        }
        
        responseMsg = encryptAndSign(responseMsg,
        		AlipayConfig.ALI_PUB_KEY,
        		AlipayConfig.RSA2_PRI_KEY,"GBK",
                false, true, AlipayConfig.SIGN_TYPE);

        //http 内容应答
        response.reset();
        response.setContentType("text/xml;charset=GBK");
        PrintWriter printWriter = response.getWriter();
        printWriter.print(responseMsg);
        printWriter.flush();
        response.flushBuffer();
        printWriter.close();
	}
	
	/**
	 * 
	 * 网关响应信息加密
	 */
	public static String encryptAndSign(String bizContent, String alipayPublicKey, String cusPrivateKey, String charset,
			boolean isEncrypt, boolean isSign, String signType) throws AlipayApiException {
		StringBuilder sb = new StringBuilder();
		if (charset == null || charset.trim().length() == 0) {
			charset = AlipayConstants.CHARSET_GBK;
		}
		sb.append("<?xml version=\"1.0\" encoding=\"" + charset + "\"?>");
		if (isEncrypt) {// 加密
			sb.append("<alipay>");
			String encrypted = AlipaySignature.rsaEncrypt(bizContent, alipayPublicKey, charset);
			sb.append("<response>" + encrypted + "</response>");
			sb.append("<encryption_type>AES</encryption_type>");
			if (isSign) {
				String sign = AlipaySignature.rsaSign(encrypted, cusPrivateKey, charset, signType);
				sb.append("<sign>" + sign + "</sign>");
				sb.append("<sign_type>");
				sb.append(signType);
				sb.append("</sign_type>");
			}
			sb.append("</alipay>");
		} else if (isSign) {// 不加密，但需要签名
			sb.append("<alipay>");
			sb.append("<response>" + bizContent + "</response>");
			String sign = AlipaySignature.rsaSign(bizContent, cusPrivateKey, charset, signType);
			sb.append("<sign>" + sign + "</sign>");
			sb.append("<sign_type>");
			sb.append(signType);
			sb.append("</sign_type>");
			sb.append("</alipay>");
		} else {// 不加密，不加签
			sb.append(bizContent);
		}
		return sb.toString();
	}
	
	public static String getString(String para) {
		return para == null?"":para;
	}
}