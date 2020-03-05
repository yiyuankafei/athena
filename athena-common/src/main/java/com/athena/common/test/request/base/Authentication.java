package com.athena.common.test.request.base;

import lombok.Data;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Authentication")
@Data
public class Authentication {
	
	private String OrgID = "12345";
	
	private String UserName = "username";
	
	private String AuthKey = "xxxxxxxxxxx";
	
	private String Version = "1.0";

}
