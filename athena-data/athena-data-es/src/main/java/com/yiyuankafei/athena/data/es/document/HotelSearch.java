package com.yiyuankafei.athena.data.es.document;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;

@Document(indexName = "haoqiao-hotel")
@Getter
@Setter
public class HotelSearch implements Serializable {

	private static final long serialVersionUID = -604442519417232203L;

	@Id
	private String id;
	
	/** 酒店名 */
	@Field(type = FieldType.Text, analyzer = "smartcn")
	private String nameChn;
	
	/** 酒店英文名 */
	@Field(type = FieldType.Text)
	private String nameEng;
	
	/** 酒店中文地址 */
	private String address;
	
	/** 酒店英文地址 */
	private String addressEng;
	
	/** 所在城市 */
	@Field(type = FieldType.Long)
	private Long cityId;
	
	/** 星级 */
	@Field(type = FieldType.Integer)
	private Integer star;
	
	/** 最低价 */
	@Field(type = FieldType.Double)
	private Double minPrice;
	
	/** 评分 */
	@Field(type = FieldType.Double)
	private String commentScore;
	
	/** 设施列表 */
	@Field(type = FieldType.Text, analyzer = "smartcn")
	private String facilities;
	
	/** 酒店地址GEO */
	@GeoPointField 
	private GeoPoint  location;
	
	/** 酒店地址经度 */
	private Double longitude;
	
	/** 酒店地址纬度 */
	private Double latitude;
	
	/** 图片列表 */
	private String imageList;
	
	/** 备注 */
	private String remarks;
	
	/** 简介 */
	private String introduction;
	
	/** 当日剩余房间数 */
	@Field(type = FieldType.Integer)
	private Integer d0;
	
	/** T+1 剩余房间数 */
	@Field(type = FieldType.Integer)
	private Integer d1;
	
	/** T+2 剩余房间数 */
	@Field(type = FieldType.Integer)
	private Integer d2;
	
	/** T+3 剩余房间数 */
	@Field(type = FieldType.Integer)
	private Integer d3;
	
	/** T+4 剩余房间数 */
	@Field(type = FieldType.Integer)
	private Integer d4;
	
	/** T+5 剩余房间数 */
	@Field(type = FieldType.Integer)
	private Integer d5;
	
	/** T+6 剩余房间数 */
	@Field(type = FieldType.Integer)
	private Integer d6;
	
	/** T+7 剩余房间数 */
	@Field(type = FieldType.Integer)
	private Integer d7;
	
	/** T+8 剩余房间数 */
	@Field(type = FieldType.Integer)
	private Integer d8;
	
	/** T+9 剩余房间数 */
	@Field(type = FieldType.Integer)
	private Integer d9;
	
	/** T+10 剩余房间数 */
	@Field(type = FieldType.Integer)
	private Integer d10;
	
	/** T+11 剩余房间数 */
	@Field(type = FieldType.Integer)
	private Integer d11;
	
	/** T+12 剩余房间数 */
	@Field(type = FieldType.Integer)
	private Integer d12;
	
	/** T+13 剩余房间数 */
	@Field(type = FieldType.Integer)
	private Integer d13;
	
	/** T+14 剩余房间数 */
	@Field(type = FieldType.Integer)
	private Integer d14;
	
	/** T+15 剩余房间数 */
	@Field(type = FieldType.Integer)
	private Integer d15;
	
	/** T+16 剩余房间数 */
	@Field(type = FieldType.Integer)
	private Integer d16;
	
	/** T+17 剩余房间数 */
	@Field(type = FieldType.Integer)
	private Integer d17;
	
	/** T+18 剩余房间数 */
	@Field(type = FieldType.Integer)
	private Integer d18;
	
	/** T+19 剩余房间数 */
	@Field(type = FieldType.Integer)
	private Integer d19;
	
	/** T+20 剩余房间数 */
	@Field(type = FieldType.Integer)
	private Integer d20;
	
	/** T+21 剩余房间数 */
	@Field(type = FieldType.Integer)
	private Integer d21;
	
	/** T+22 剩余房间数 */
	@Field(type = FieldType.Integer)
	private Integer d22;
	
	/** T+23 剩余房间数 */
	@Field(type = FieldType.Integer)
	private Integer d23;
	
	/** T+24 剩余房间数 */
	@Field(type = FieldType.Integer)
	private Integer d24;
	
	/** T+25 剩余房间数 */
	@Field(type = FieldType.Integer)
	private Integer d25;
	
	/** T+26 剩余房间数 */
	@Field(type = FieldType.Integer)
	private Integer d26;
	
	/** T+27 剩余房间数 */
	@Field(type = FieldType.Integer)
	private Integer d27;
	
	/** T+28 剩余房间数 */
	@Field(type = FieldType.Integer)
	private Integer d28;
	
	/** T+29 剩余房间数 */
	@Field(type = FieldType.Integer)
	private Integer d29;
	
	/** T+30 剩余房间数 */
	@Field(type = FieldType.Integer)
	private Integer d30;
	
	/** T+31 剩余房间数 */
	@Field(type = FieldType.Integer)
	private Integer d31;

	/** T+32 剩余房间数 */
	@Field(type = FieldType.Integer)
	private Integer d32;
	
	/** 更新时间 */
	@Field(index = true, type = FieldType.Long)
	private Date updateTime;
}
