package com.athena.thirdpart.message.application.request;

import lombok.Data;

@Data
public class CreateTemplateRequest {
	
	private String remark;
	
	private Integer international = 0;
	
	private String sig;
	
	private String text;
	
	private Long time;
	
	private String title;
	
	private Integer type = 0;

}
