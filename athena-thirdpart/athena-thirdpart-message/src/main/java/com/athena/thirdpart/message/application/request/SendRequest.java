package com.athena.thirdpart.message.application.request;

import java.util.List;

import lombok.Data;

import com.athena.thirdpart.message.application.entity.Tel;

@Data
public class SendRequest {
	
	private String ext = ""; //用户的 session 内容，腾讯 server 回包中会原样返回，可选字段，不需要就是设置为空
	
	private String extend = ""; //短信码号扩展号，格式为纯数字串，其他格式无效。默认没有开通，开通请联系 腾讯云短信技术支持
	
	private List<String> params; //模板参数，具体使用方法可参考注【1】。若模板没有参数，请设置为空数组
	
	private String sig; //App 凭证，具体计算方式见下注
	
	private String sign = ""; //短信签名，此处应填写审核通过的签名内容，非签名 ID，如果使用默认签名，该字段可缺省
	
	private Tel tel; //国际电话号码，格式依据 e.164 标准为: +[国家码][手机号] ，示例如：+8613711112222， 其中前面有一个 + 符号 ，86 为国家码，13711112222 为手机号
	
	private Long time; //请求发起时间，UNIX 时间戳（单位：秒），如果和系统时间相差超过 10 分钟则会返回失败
	
	private Long tpl_id; //模板 ID，在 控制台 审核通过的模板 ID

}
