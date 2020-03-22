package com.athena.search.es.application.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com.alibaba.fastjson.annotation.JSONField;

@Getter
@Setter
public class ImportCityVo {
	
	@JSONField(name = "CityList")
	List<HotelCity> cityList;

}
