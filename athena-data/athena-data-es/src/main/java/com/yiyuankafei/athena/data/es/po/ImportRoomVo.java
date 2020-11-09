package com.yiyuankafei.athena.data.es.po;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com.alibaba.fastjson.annotation.JSONField;

@Getter
@Setter
public class ImportRoomVo {
	
	@JSONField(name = "HotelList")
	List<ImportHotelRoomVo> hotelList;

}
