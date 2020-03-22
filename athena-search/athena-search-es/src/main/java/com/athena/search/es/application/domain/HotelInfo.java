package com.athena.search.es.application.domain;


import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * 酒店信息
 */
@Getter
@Setter
public class HotelInfo implements Serializable {

	private static final long serialVersionUID = 4172120566974231198L;

	/** 主键ID */
	private Long id;
	
	/** 酒店ID */
	private Long hotelId;
	
	/** 城市ID */
	private Long cityId;
	
	/** 省份ID */
	private Long stateId;
	
	/** 国家ID */
	private Long countryId;
	
	/** 酒店中文名 */
	private String nameChn;
	
	/** 酒店英文名 */
	private String nameEng;
	
	/** 酒店中文地址 */
	private String address;
	
	/** 酒店英文地址 */
	private String addressEng;
	
	/** 酒店地址经度 */
	private Double longitude;
	
	/** 酒店地址纬度 */
	private Double latitude;
	
	/** 星级 */
	private Integer star;
	
	/** 评分 */
	private String commentScore;
	
	/** 备注 */
	private String remarks;
	
	/** 简介 */
	private String introduction;
	
	/** 设施列表 */
	private String facilities;
	
	/** 图片列表 */
	private String imageList;
	
	/** 最低价 */
	private BigDecimal minPrice;
	
	/** 币种 */
	private String currency;
	
	/** 数据版本号 */
	private Integer version;

}
