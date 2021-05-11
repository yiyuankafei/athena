package com.application.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.util.SignUtil;
import com.application.util.XmlUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.athena.common.response.ResEnv;
import com.athena.common.util.HttpClientUtil;

@Controller
public class PayController {
	
	private static String appId = "";
	
	private static String mchId = ""; //商户号
	
	private static String notifyUrl = "https://xxx/pay/callback"; //异步通知地址
	
	private static String apiKey = ""; //API密钥  在公众平台设置，用于签名和验签
	
	private static String payUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder"; //统一下单
	
	private static String queryUrl = "https://api.mch.weixin.qq.com/pay/orderquery"; //查询订单
	
	private static String closeUrl = "https://api.mch.weixin.qq.com/pay/closeorder"; //关闭订单
	
	private static String refundUrl = "https://api.mch.weixin.qq.com/pay/refund"; //退款     <需要证书>
	
	private static String refundQueryUrl = "https://api.mch.weixin.qq.com/pay/refundquery"; //退款查询
	
	private static String downloadBillUrl = "https://api.mch.weixin.qq.com/pay/downloadbill"; //对账单

	private static String downloadFundFlowUrl = "https://api.mch.weixin.qq.com/pay/downloadfundflow"; //下载资金账单
	
	private static String returnUrl = "";

	@RequestMapping("/pay")
	public ModelAndView pay(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String clientType = request.getHeader("User-Agent");
    	//Androw  iphone windows-phone  移动端
    	if (clientType.indexOf("xxx") > 1) {
    		//移动端支付请求
    		//直接唤醒微信支付
    		String orderNo = ""; // 生成订单号
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("appid", appId);
			map.put("mch_id", mchId);
			map.put("device_info","");
			map.put("notify_url", notifyUrl);
			map.put("trade_type", "MWEB"); // 交易类型
			map.put("out_trade_no", orderNo);
			map.put("body", "");
			map.put("total_fee", 1);
			map.put("nonce_str", "123456"); // 随机串
			map.put("spbill_create_ip", "xxx"); // 终端IP
			map.put("sign", ""); // 签名
			String xml = ""; //map转xml
			System.out.println(xml);
			InputStream in = null; //HttpClientUtil.doPostXml(payUrl ,xml).getEntity().getContent(); // 发送xml消息
			String mweb_url = ""; //getElementValue(in,"mweb_url");   获取返回的mweb_url
			// 拼接跳转地址 
			mweb_url += "&redirect_url=" + URLEncoder.encode(returnUrl, "GBK");
			/*order.setOrderNo(orderNo); // 设置订单号
     		orderService.save(order); // 保存订单信息*/ 
    		response.sendRedirect(mweb_url);
     		return null;
    	} else {
    		// WEB端支付请求
    		//返回页面，在img中调用/pay/qrcode生成支付二维码
    		return new ModelAndView();
    	}
	}
	
	
	@RequestMapping("/pay/qrcode")
	public void qrcode(String orderId, HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("app_id", appId);
		map.put("mch_id", mchId);
		map.put("nonce_str", "123456"); //随机字符串
		map.put("notify_url", notifyUrl);
		map.put("trade_type", "NATIVE"); //PC端
		map.put("out_trade_no", orderId);
		map.put("body", "测试商品"); //商品描述
		map.put("total_fee", 1000); //订单金额   单位：分
		map.put("spbill_create_ip", ""); //终端IP
		map.put("sign", ""); //签名  具体参考官方文档规则
		
		String xml = ""; //将map转为xml
		
		String result = HttpClientUtil.doPostXml(payUrl, xml);
		
		//DOM4J解析结果
		String codeUrl = ""; //支付二维码地址
		String prePayId = ""; //预支付ID
		
		//将支付地址生成二维码图片写入response
	}
	
	@RequestMapping("/pay/callback")
	public void callback(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		InputStream inputStream = request.getInputStream();
		StringBuilder builder = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
		
		String s;
		while ((s = reader.readLine()) != null) {
			builder.append(s);
		}
		inputStream.close();
		
		String content = builder.toString();
		//转化XML为对象， 过去通知内容
		//验签
		
	}
	
	@RequestMapping("/pay/status")
	public ResEnv<?> status(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//查看支付状态，返回前台
		Integer payStatus = 1;
		return ResEnv.success(payStatus);
	}

	public static void main(String[] args) throws Exception{
		Map<String, Object> map = new HashMap<>();
		map.put("appid", "11");
		map.put("mch_id", "22");
		map.put("nonce_str", "A583E0098AEF1236C2910"); //随机字符串
		map.put("out_trade_no", "202105100558096383");
		String sign = SignUtil.generateSign(map, "33");
		map.put("sign", sign);

		String xml = XmlUtil.toXml(map);
		String result = HttpClientUtil.doPostXml("https://api.mch.weixin.qq.com/pay/orderquery", xml);
		System.out.println(result);

	}

}
