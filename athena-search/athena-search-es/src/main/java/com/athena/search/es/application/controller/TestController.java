package com.athena.search.es.application.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.athena.search.es.application.document.HaoqiaoDocument;
import com.athena.search.es.application.document.HotelDocument;
import com.athena.search.es.application.domain.HotelCity;
import com.athena.search.es.application.domain.HotelCountry;
import com.athena.search.es.application.domain.ImportCityVo;
import com.athena.search.es.application.domain.ImportCountryVo;
import com.athena.search.es.application.domain.ImportHotelVo;
import com.athena.search.es.application.query.Query;
import com.athena.search.es.application.repository.HaoqiaoRepository;
import com.athena.search.es.application.repository.HotelRepository;

@RestController
@Slf4j
public class TestController {
	
	@Autowired
	HaoqiaoRepository repository;
	
	@Autowired
	HotelRepository hotelRepository;
	
	@RequestMapping("/test")
	public String test() {
		return "1111111111111";
	}
	
	@RequestMapping("/delete")
	public String delete() {
		repository.deleteAll();
		return "22222222222";
	}
	
	@RequestMapping("/findAll")
	public Page<HaoqiaoDocument> findAll(String id, Integer pageSize, Integer pageNumber) {
		System.out.println(id);
		System.out.println(pageSize);
		System.out.println(pageNumber);
		Page<HaoqiaoDocument> findAll = repository.findAll(PageRequest.of(pageNumber, pageSize));
		return findAll;
	}
	
	@RequestMapping("/findById")
	public HaoqiaoDocument findById(String id) {
		Optional<HaoqiaoDocument> findById = repository.findById(id);
		return findById.get();
	}
	
	@RequestMapping("/search")
	public Page<HaoqiaoDocument> search(String name, Integer pageSize, Integer pageNumber) {
		
		
		BoolQueryBuilder keywordSearchQuery = QueryBuilders.boolQuery();
		keywordSearchQuery.must(QueryBuilders.multiMatchQuery(name, "nameChn", "nameEng", "cityNameChn", "cityNameEng"));
		
		NativeSearchQuery query = new NativeSearchQueryBuilder()
			.withQuery(keywordSearchQuery)
			.withPageable(PageRequest.of(pageNumber, pageSize))
			.build();
		
		Page<HaoqiaoDocument> search = repository.search(query);
		return search;
	}
	
	@RequestMapping("/s1")
	public Page<HotelDocument> search(String name) {
		
		
		BoolQueryBuilder keywordSearchQuery = QueryBuilders.boolQuery();
		keywordSearchQuery.must(QueryBuilders.multiMatchQuery(name, "nameChn", "nameEng"));
		
		NativeSearchQuery query = new NativeSearchQueryBuilder()
			.withQuery(keywordSearchQuery)
			.withPageable(PageRequest.of(1, 20))
			.build();
		
		Page<HotelDocument> search = hotelRepository.search(query);
		return search;
	}
	
	@RequestMapping("/hotel")
	public Page<HotelDocument> search(Query request) {
		
		System.out.println(request);
		
		
		BoolQueryBuilder keywordSearchQuery = QueryBuilders.boolQuery();
		if (request.getName() != null) {
			keywordSearchQuery.should(QueryBuilders.multiMatchQuery(request.getName(), "nameChn", "nameEng"));
		}
		if (request.getFan() != null) {
			keywordSearchQuery.should(QueryBuilders.matchQuery("facilities", request.getFan().stream().collect(Collectors.joining(","))));
		}
		
		
		BoolQueryBuilder termSearchQuery = QueryBuilders.boolQuery();
		if (request.getCityId() != null) {
			termSearchQuery.must(QueryBuilders.termQuery("cityId", request.getCityId()));
		}
		if (request.getMinPrice() != null) {
			termSearchQuery.must(QueryBuilders.rangeQuery("minPrice").from(request.getMinPrice()));
		}
		if (request.getMaxPrice() != null) {
			termSearchQuery.must(QueryBuilders.rangeQuery("minPrice").to(request.getMaxPrice()));
		}
		if (request.getRoomCount() != null) {
			termSearchQuery.must(QueryBuilders.rangeQuery("d0").from(request.getRoomCount()));
		}
		if (request.getRoomCount2() != null) {
			termSearchQuery.must(QueryBuilders.rangeQuery("d2").from(request.getRoomCount2()));
		}
		if (request.getStarList() != null) {
			termSearchQuery.must(QueryBuilders.termsQuery("star", request.getStarList()));
		}
		if (request.getIdList() != null) {
			termSearchQuery.must(QueryBuilders.termsQuery("_", request.getIdList()));
		}
		
		if(request.getLongitude() != null) {
			System.out.println("ASDFGH");
			termSearchQuery.must(QueryBuilders.geoDistanceQuery("location")
											  .distance(1, DistanceUnit.KILOMETERS)
											  .point(new GeoPoint(request.getLatitude(), request.getLongitude()))
								);
											  
					
		}
		
		
		BoolQueryBuilder searchQuery = QueryBuilders.boolQuery().must(termSearchQuery).must(keywordSearchQuery);
		
		List<FieldSortBuilder> fieldSortBuilders = new ArrayList<>();
		if (request.getSortByScore() != null) {
			fieldSortBuilders.add(SortBuilders.fieldSort("commentScore").order(SortOrder.DESC));
		}
		if (request.getSortByPrice() != null) {
			fieldSortBuilders.add(SortBuilders.fieldSort("minPrice").order(SortOrder.ASC));
		}
		
		NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
		nativeSearchQueryBuilder.withQuery(searchQuery);
		nativeSearchQueryBuilder.withPageable(PageRequest.of(request.getPageNumber(), request.getPageSize()));
		fieldSortBuilders.forEach(item -> {
			nativeSearchQueryBuilder.withSort(item);
		});
		
		Page<HotelDocument> pages = hotelRepository.search(nativeSearchQueryBuilder.build());
		
		return pages;
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
						/*HaoqiaoDocument doc = new HaoqiaoDocument();
						doc.setId("C" + item.getCityId());
						doc.setCityId(item.getCityId());
						doc.setNameChn(item.getNameChn());
						doc.setNameEng(item.getNameEng());
						doc.setStateNameChn(item.getStateNameChn());
						doc.setStateNameEng(item.getStateNameEng());
						doc.setCountryNameChn(item.getCountryNameChn());
						doc.setCountryNameEng(item.getCountryNameEng());
						doc.setType(1);
						d.add(doc);*/
						//==================================
						cityList.addAll(vo.getCityList());
					});
				}
				//importEs(d);
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
			List<HotelDocument> docList = new ArrayList<>();
			Random r = new Random();
			//Map<String, Point> pointMap = new HashMap<>();
			try (BufferedReader reader = new BufferedReader(new FileReader(dataFile))) {
				String s = null;
				List<HaoqiaoDocument> d = new ArrayList<>();
				while ((s = reader.readLine()) != null) {
					ImportHotelVo vo = JSON.parseObject(s, ImportHotelVo.class);
					vo.getHotelList().forEach(item -> {
						System.out.println(item.getNameChn());
						HotelDocument doc = new HotelDocument();
						doc.setId(item.getHotelId().toString());
						doc.setNameChn(item.getNameChn());
						doc.setNameEng(item.getNameEng());
						doc.setAddress(item.getAddress());
						doc.setAddressEng(item.getAddressEng());
						doc.setLongitude(item.getLongitude());
						doc.setLatitude(item.getLatitude());
						doc.setCityId(item.getCityId());
						doc.setMinPrice(new Double(r.nextInt(2000) + ""));
						doc.setStar(item.getStar());
						doc.setCommentScore(item.getCommentScore());
						doc.setFacilities(item.getFacilities());
						doc.setImageList(item.getImageList());
						doc.setLocation(new GeoPoint(item.getLatitude(), item.getLongitude()));
						doc.setD0(3);
						doc.setD2(9);
						docList.add(doc);
						//pointMap.put(String.valueOf(item.getHotelId()), new Point(item.getLongitude(), item.getLatitude()));
						//==================================
						/*HaoqiaoDocument doc = new HaoqiaoDocument();
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
							doc.setType(2);
						}
						d.add(doc);*/
						//==================================
					});
					//设置酒店最低价
					//setMinPrice(vo.getHotelList());
					//上传酒店图片
					//uploadImages(vo.getHotelList());
					//repository.insertBatch(vo.getHotelList());
					//opsForGeo.add(CommonConstant.CACHE_HOTEL_GEO, pointMap);
					//导入ES
					//importEs(d);
					hotelRepository.saveAll(docList);
				}
			}
		}
	}


	private void importEs(List<HaoqiaoDocument> d) {
		repository.saveAll(d);
	}

}
