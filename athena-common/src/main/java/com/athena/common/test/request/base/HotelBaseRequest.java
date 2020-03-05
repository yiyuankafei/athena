package com.athena.common.test.request.base;

import lombok.Data;


@Data
public class HotelBaseRequest {
	
	private String ReqType;
	
	private String Currency = "CNY";
	
}
