package com.yiyuankafei.athena.data.es.po;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com.alibaba.fastjson.annotation.JSONField;

@Getter
@Setter
public class ImportHotelRoomVo {
	
	@JSONField(name = "CityId")
	private Long cityId;
	
	@JSONField(name = "HotelId")
	private Long hotelId;
	
	@JSONField(name = "RoomList")
	private List<HotelRoom> roomList;

}
