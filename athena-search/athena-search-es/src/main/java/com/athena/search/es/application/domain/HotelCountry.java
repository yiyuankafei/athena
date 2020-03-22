package com.athena.search.es.application.domain;


import java.io.Serializable;

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
	private Integer version;
	

}
