package com.athena.common.test.request.base;

import lombok.Data;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("AvailabilityRequest")
@Data
public class AvailabilityRequest <T> {
	
	@XStreamAsAttribute()
	private String sessionId;
	
	private Authentication Authentication = new Authentication();
	
	private T HotelRequest;

	public AvailabilityRequest(String sessionId, T hotelRequest) {
		super();
		this.sessionId = sessionId;
		HotelRequest = hotelRequest;
	}

}
