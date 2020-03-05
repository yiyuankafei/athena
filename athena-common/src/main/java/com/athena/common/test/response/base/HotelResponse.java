package com.athena.common.test.response.base;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Data;

@Data
public class HotelResponse {
	
	private String ResponseType;
	
	private Integer Code;
	
	private Integer SubCode;
	
	private String Msg;

}
