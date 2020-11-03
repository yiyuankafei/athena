package com.yiyuankafei.athena.data.es.po;


import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * 国家信息
 */
@Getter
@Setter
public class HotelCountry implements Serializable {

	private static final long serialVersionUID = -4591552807781647474L;
	
	/** 主键ID */
	private Long id;
	
	/** 国家ID */
	private Long countryId;
	
	/** 国家中文名 */
	private String nameChn;
	
	/** 国家英文名 */
	private String nameEng;
	
	/** 数据版本号 */
	private Integer version = 0;
	
	/** 数据状态：0-未启用 1-正常 *//*
	@DefaultValue(type = DefaultValueType.STATIC_VAL, staticVal = "0")
	private Integer status;*/
	
	/** 创建时间 */
	private Date createTime;
	
	/** 修改时间 */
	private Date updateTime;

}
