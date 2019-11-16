package com.application.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.application.entity.BrainTreeVo;
import com.athena.common.response.ResEnv;
import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Environment;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;

@Controller
public class PayController {
	
	private static String merchantId = "";
	
	private static String publicKey = "";
	
	private static String privateKey = "";
	
	private static String mode = "";
	
	/**
	 * 
	 * 获取clientToken
	 */
	@RequestMapping("/getToken")
	public ResEnv<String> getToken() {
		BraintreeGateway gateway = getBraintreeGateway();
		return ResEnv.success(gateway.clientToken().generate());
	}
	
	/**
	 * 
	 * 结算
	 */
	@RequestMapping("/pay")
	public ResEnv<?> transaction(HttpServletRequest requst, HttpSession session) { 
		
		/*User loginUser = (User)session.getAttribute(CommonConstant.SESSION_USER);
		CommonReq<BrainTreeVo> commonReq = JsonRevetUtils.parseJson(
				((JSONObject)req.getAttribute(CommonConstant.COMMON_REQ)).toString(),new TypeReference<CommonReq<BrainTreeVo>>(){});
		BrainTreeVo brainTreeVo = commonReq.getReqData();*/
		BrainTreeVo brainTreeVo = new BrainTreeVo();
		
		BraintreeGateway gateway = getBraintreeGateway();
		TransactionRequest request = new TransactionRequest().amount(brainTreeVo.getAmount()).paymentMethodNonce("123456")
				.options().submitForSettlement(true).done();
		Result<Transaction> result = gateway.transaction().sale(request);
		//结果入库
		
		return ResEnv.success();
	}
	
	/**
	 * 
	 * 提现申请
	 *//*
	@RequestMapping("/extract")
	public ResEnv extract(HttpServletRequest req,HttpSession session) { 
		
	}*/
	
	
	public static BraintreeGateway getBraintreeGateway() {
		return new BraintreeGateway(mode.equals("sandbox")?Environment.SANDBOX:Environment.PRODUCTION, merchantId,publicKey, privateKey);
	}
}
