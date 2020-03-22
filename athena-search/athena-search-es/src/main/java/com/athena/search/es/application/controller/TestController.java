package com.athena.search.es.application.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.athena.search.es.application.document.HaoqiaoDocument;
import com.athena.search.es.application.domain.HotelCity;
import com.athena.search.es.application.domain.HotelCountry;
import com.athena.search.es.application.domain.ImportCityVo;
import com.athena.search.es.application.domain.ImportCountryVo;
import com.athena.search.es.application.domain.ImportHotelVo;
import com.athena.search.es.application.repository.HaoqiaoRepository;

@RestController
@Slf4j
public class TestController {
	
	@Autowired
	HaoqiaoRepository repository;
	
	@RequestMapping("/test")
	public String test() {
		return "1111111111111";
	}
	
	@RequestMapping("/delete")
	public String delete() {
		repository.deleteAll();
		return "22222222222";
	}
	
	
	@RequestMapping("/import")
	public String importData() throws Exception {
		log.info("开始导入静态数据");
		String targetFileName = "D:\\ftp\\data";
		
		//开始导入数据
		File baseFile = new File(targetFileName);
		File[] dataFiles = baseFile.listFiles();
		
		List<HotelCountry> countryList = new ArrayList<>();
		List<HotelCity> cityList = new ArrayList<>();
		Map<String, List<File>> fileNameMap = Arrays.asList(dataFiles).stream().collect(Collectors.groupingBy(File::getName));
		
		importCountry(fileNameMap.get("countryList").get(0), countryList);
		importCity(fileNameMap.get("cityList").get(0), countryList, cityList);
		importHotel(fileNameMap.get("hotelList").get(0), cityList);
		log.info("导入静态数据完成");
		return "导入静态数据完成";
		
	}
	
	/**
	 * 
	 * 导入国家信息
	 */
	private void importCountry(File file, List<HotelCountry> countryList) throws Exception {
		File[] dataFiles = file.listFiles();
		for (File dataFile : dataFiles) {
			try (BufferedReader reader = new BufferedReader(new FileReader(dataFile))) {
				String s = null;
				while ((s = reader.readLine()) != null) {
					ImportCountryVo vo = JSON.parseObject(s, ImportCountryVo.class);
					countryList.addAll(vo.getCountryList());
				}
			}
		}
	}
	
	/**
	 * 
	 * 导入城市信息
	 */
	private void importCity(File file, List<HotelCountry> countryList, List<HotelCity> cityList) throws Exception {
		Map<Long, List<HotelCountry>> countryMap = countryList.stream().collect(Collectors.groupingBy(HotelCountry::getCountryId));
		File[] dataFiles = file.listFiles();
		for (File dataFile : dataFiles) {
			try (BufferedReader reader = new BufferedReader(new FileReader(dataFile))) {
				String s = null;
				List<HaoqiaoDocument> d = new ArrayList<>();
				while ((s = reader.readLine()) != null) {
					ImportCityVo vo = JSON.parseObject(s, ImportCityVo.class);
					vo.getCityList().forEach(item -> {
						List<HotelCountry> list = countryMap.get(item.getCountryId());
						if (list != null) {
							item.setCountryNameChn(list.get(0).getNameChn());
							item.setCountryNameEng(list.get(0).getNameEng());
						}
						//==================================
						HaoqiaoDocument doc = new HaoqiaoDocument();
						doc.setId("C" + item.getCityId());
						doc.setCityId(item.getCityId());
						doc.setNameChn(item.getNameChn());
						doc.setNameEng(item.getNameEng());
						doc.setStateNameChn(item.getStateNameChn());
						doc.setStateNameEng(item.getStateNameEng());
						doc.setCountryNameChn(item.getCountryNameChn());
						doc.setCountryNameEng(item.getCountryNameEng());
						doc.setType(1);
						d.add(doc);
						//==================================
						cityList.addAll(vo.getCityList());
					});
				}
				importEs(d);
			}
		}
	}
	
	
	/**
	 * 
	 * 导入酒店信息
	 */
	private void importHotel(File file, List<HotelCity> cityList) throws Exception {
		
		Map<Long, List<HotelCity>> cityMap = cityList.stream().collect(Collectors.groupingBy(HotelCity::getCityId));
		
		File[] dataFiles = file.listFiles();
		//GeoOperations<String, String> opsForGeo = redis.opsForGeo();
		for (File dataFile : dataFiles) {
			//Map<String, Point> pointMap = new HashMap<>();
			try (BufferedReader reader = new BufferedReader(new FileReader(dataFile))) {
				String s = null;
				List<HaoqiaoDocument> d = new ArrayList<>();
				while ((s = reader.readLine()) != null) {
					ImportHotelVo vo = JSON.parseObject(s, ImportHotelVo.class);
					vo.getHotelList().forEach(item -> {
						System.out.println(item.getNameChn());
						
						//pointMap.put(String.valueOf(item.getHotelId()), new Point(item.getLongitude(), item.getLatitude()));
						//==================================
						HaoqiaoDocument doc = new HaoqiaoDocument();
						doc.setId("H" + item.getHotelId());
						doc.setCityId(item.getCityId());
						doc.setNameChn(item.getNameChn());
						doc.setNameEng(item.getNameEng());
						List<HotelCity> list = cityMap.get(item.getCityId());
						if (list != null) {
							HotelCity city = list.get(0);
							doc.setStateNameChn(city.getStateNameChn());
							doc.setStateNameEng(city.getStateNameEng());
							doc.setCountryNameChn(city.getCountryNameChn());
							doc.setCountryNameEng(city.getCountryNameEng());
							doc.setCityNameChn(city.getNameChn());
							doc.setCityNameEng(city.getNameEng());
							doc.setType(1);
						}
						d.add(doc);
						//==================================
					});
					//设置酒店最低价
					//setMinPrice(vo.getHotelList());
					//上传酒店图片
					//uploadImages(vo.getHotelList());
					//repository.insertBatch(vo.getHotelList());
					//opsForGeo.add(CommonConstant.CACHE_HOTEL_GEO, pointMap);
					//导入ES
					importEs(d);
				}
			}
		}
	}


	private void importEs(List<HaoqiaoDocument> d) {
		repository.saveAll(d);
	}

	
}
