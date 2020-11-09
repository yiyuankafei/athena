package com.yiyuankafei.athena.data.es.document;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "haoqiao-aggr")
@Getter
@Setter
public class AggrSearch implements Serializable {

	private static final long serialVersionUID = 2136777504890987505L;
	
	@Id
	private String id;
	
	/** 数据类型 1-城市 2-酒店 */
	private Integer type;
	
	/** 酒店ID */
	private Long hotelId;
	
	/** 城市ID */
	private Long cityId;
	
	/** 关键字中文名 */
	@Field(type = FieldType.Text, analyzer = "smartcn")
	private String nameChn;
	
	/** 关键字英文名 */
	private String nameEng;
	
	/** 城市中文名 */
	@Field(type = FieldType.Text, analyzer = "smartcn")
	private String cityNameChn;
	
	/** 城市英文名 */
	private String cityNameEng;
	
	/** 酒店中文名 */
	@Field(type = FieldType.Text, analyzer = "smartcn")
	private String hotelNameChn;
	
	/** 酒店英文名 */
	private String hotelNameEng;
	
	/** 省份中文名 */
	private String stateNameChn;
	
	/** 省份英文名 */
	private String stateNameEng;
	
	/** 国家中文名 */
	private String countryNameChn;
	
	/** 国家英文名 */
	private String countryNameEng;
	
}
