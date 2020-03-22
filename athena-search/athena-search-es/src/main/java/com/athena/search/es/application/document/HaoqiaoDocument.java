package com.athena.search.es.application.document;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "rewardsbay", type="haoqiao")
@Getter
@Setter
public class HaoqiaoDocument implements Serializable {

	private static final long serialVersionUID = -9218546106031710636L;
	
	@Id
	private String id;
	
	@Field(type = FieldType.Text, analyzer = "ik_max_word")
	private String nameChn;
	
	private String nameEng;
	
	@Field(type = FieldType.Text, analyzer = "smartcn")
	private String cityNameChn;
	
	private String cityNameEng;
	
	@Field(type = FieldType.Text, analyzer = "smartcn")
	private String stateNameChn;
	
	private String stateNameEng;
	
	@Field(type = FieldType.Text, analyzer = "smartcn")
	private String countryNameChn;
	
	private String countryNameEng;
	
	private Integer type;
	
	private Long hotelId;
	
	private Long cityId;
	
	

}
