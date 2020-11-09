package com.yiyuankafei.athena.data.es.po;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com.alibaba.fastjson.annotation.JSONField;

@Getter
@Setter
public class ImportHotelVo {
	
	@JSONField(name = "HotelList")
	List<HotelInfo> hotelList;

}
