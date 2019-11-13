package com.athena.thirdpart.message.application.response;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class SendResponse {
	
	private Long result;
	
	private String errmsg;
	
	private String ext;
	
	private BigDecimal fee;
	
	private String sid;

}
