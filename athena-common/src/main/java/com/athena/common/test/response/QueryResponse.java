package com.athena.common.test.response;

import java.math.BigDecimal;

import lombok.Data;

import com.athena.common.test.response.base.HotelResponse;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("HotelResponse")
@Data
public class QueryResponse extends HotelResponse {
	
	private String BookingID;
	
	private String PartnerBookingID;
	
	private Integer BookingStatus;
	
	private BigDecimal CancelCharge;
	
	private String Currency;

}
