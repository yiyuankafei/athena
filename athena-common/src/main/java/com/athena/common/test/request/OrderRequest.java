package com.athena.common.test.request;

import lombok.Data;

import com.athena.common.test.request.base.HotelBaseRequest;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("HotelRequest")
@Data
public class OrderRequest extends HotelBaseRequest {
	
	private Integer CityID;
	
	private String CheckInDate;
	
	private String CheckOutDate;
	
	private String Nationality;
	
	private Integer Adult;
	
	private Integer Children;
	
	private String ChildrenAge;
	
	private Integer RoomCount;
	
	private String HotelID;
	
}
