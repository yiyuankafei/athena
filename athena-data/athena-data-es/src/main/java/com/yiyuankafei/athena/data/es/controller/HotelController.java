package com.yiyuankafei.athena.data.es.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yiyuankafei.athena.data.es.document.HotelSearch;
import com.yiyuankafei.athena.data.es.repository.HotelRepository;

@RestController
@RequestMapping("/hotel")
public class HotelController {
	
	@Autowired
	HotelRepository hotelRepository;
	
	@RequestMapping("/insert")
	public String insert() {
		
		HotelSearch hotelSearch = new HotelSearch();
		hotelSearch.setAddress("杭州西兴镇");
		hotelSearch.setAddressEng("hangzhou xixing town");
		hotelSearch.setCityId(1l);
		hotelSearch.setCommentScore("97");
		hotelSearch.setId("102");
		hotelSearch.setIntroduction("坐落于西兴古镇过塘行码头");
		hotelSearch.setNameChn("西兴七天酒店");
		hotelSearch.setNameEng("xixing 7d hotel");
		hotelSearch.setStar(3);
		hotelSearch.setMinPrice(102.3);
		hotelSearch.setUpdateTime(new Date());
		hotelSearch.setRemarks("含早餐--不能携带宠物");
		hotelSearch.setLatitude(1000.3);
		hotelSearch.setLongitude(1000.2);
		
		hotelRepository.save(hotelSearch);
		
		return "success";
	}

}
