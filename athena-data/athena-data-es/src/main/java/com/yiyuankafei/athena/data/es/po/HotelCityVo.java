package com.yiyuankafei.athena.data.es.po;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * 城市列表
 */
@Getter
@Setter
public class HotelCityVo implements Serializable {

	private static final long serialVersionUID = -3418939468009116482L;

	/** 城市ID */
	private Long cityId;
	
	/** 城市中文名 */
	private String nameChn;
	
	/** 国家中文名 */
	private String countryNameChn;

	public HotelCityVo(Long cityId, String nameChn, String countryNameChn) {
		super();
		this.cityId = cityId;
		this.nameChn = nameChn;
		this.countryNameChn = countryNameChn;
	}
	
}
