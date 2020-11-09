package com.yiyuankafei.athena.data.es.po;


import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;


/**
 * 
 * 酒店房型
 */
@Getter
@Setter
public class HotelRoom implements Serializable {

	private static final long serialVersionUID = 8274727734483318662L;

	/** 主键ID */
	private Long id;
	
	/** 房型ID */
	private Long roomId;
	
	/** 酒店ID */
	private Long hotelId;
	
	/** 城市ID */
	private Long cityId;
	
	/** 房型中文名 */
	private String nameChn;
	
	/** 房型英文名 */
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
