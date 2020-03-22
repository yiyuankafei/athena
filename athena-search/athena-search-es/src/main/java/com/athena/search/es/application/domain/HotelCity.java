package com.athena.search.es.application.domain;


import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;


/**
 * 
 * 城市信息
 */
@Getter
@Setter
public class HotelCity implements Serializable {

	private static final long serialVersionUID = 7387930408270639687L;

	/** 主键ID */
	private Long id;
	
	/** 城市ID */
	private Long cityId;
	
	/** 国家ID */
	private Long countryId;
	
	/** 城市中文名 */
	private String nameChn;
	
	/** 城市英文名 */
	private String nameEng;
	
	/** 国家中文名 */
	private String countryNameChn;
	
	/** 国家英文名 */
	private String countryNameEng;
	
	/** 省份ID */
	private Long stateId;
	
	/** 省份中文名 */
	private String stateNameChn;
	
	/** 省份英文名 */
	private String stateNameEng;
	
	/** 数据版本号 */
	private Integer version;
	

}
