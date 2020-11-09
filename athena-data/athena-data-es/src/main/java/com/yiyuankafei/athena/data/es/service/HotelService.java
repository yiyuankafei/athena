package com.yiyuankafei.athena.data.es.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.yiyuankafei.athena.data.es.document.AggrSearch;
import com.yiyuankafei.athena.data.es.document.HotelSearch;
import com.yiyuankafei.athena.data.es.po.HotelCity;
import com.yiyuankafei.athena.data.es.po.HotelCityVo;
import com.yiyuankafei.athena.data.es.po.HotelCountry;
import com.yiyuankafei.athena.data.es.po.HotelInfo;
import com.yiyuankafei.athena.data.es.po.HotelRoom;
import com.yiyuankafei.athena.data.es.po.ImportCityVo;
import com.yiyuankafei.athena.data.es.po.ImportCountryVo;
import com.yiyuankafei.athena.data.es.po.ImportHotelRoomVo;
import com.yiyuankafei.athena.data.es.po.ImportHotelVo;
import com.yiyuankafei.athena.data.es.po.ImportRoomVo;
import com.yiyuankafei.athena.data.es.repository.AggrSearchRepository;
import com.yiyuankafei.athena.data.es.repository.HotelSearchRepository;
import com.yiyuankafei.athena.data.es.util.PinYinUtil;


@Service
@Slf4j
public class HotelService {
	
private static final List<String> TABLE_NAME = new ArrayList<>(Arrays.asList("hotel_country", "hotel_city", "hotel_info", "hotel_room"));
	
	
	
	@Autowired
	AggrSearchRepository aggrSearchRepository;
	
	@Autowired
	HotelSearchRepository hotelSearchRepository;
	
	private static  ExecutorService threadPool = Executors.newFixedThreadPool(100);
	

	public void downloadBaseInfo() throws Exception {
		
		String targetFileName = "C:\\Users\\Administrator\\Desktop\\生活码头文档\\好巧资料\\好巧测试环境静态数据";
		
		//开始导入数据
		File baseFile = new File(targetFileName);
		File[] dataFiles = baseFile.listFiles();
		
		//新建版本号
//		HotelDataVersion version = new HotelDataVersion();
//		repository.insert(version);
//		log.info("【最新版本号:{}】", version.getId());
//		Thread.sleep(2000);
//		
		List<HotelCountry> countryList = new ArrayList<>();
		List<HotelCity> cityList = new ArrayList<>();
		Map<String, List<File>> fileNameMap = Arrays.asList(dataFiles).stream().collect(Collectors.groupingBy(File::getName));
		
		importCountry(fileNameMap.get("countryList").get(0), countryList);
		importCity(fileNameMap.get("cityList").get(0), countryList, cityList);
		importHotel(fileNameMap.get("hotelList").get(0), cityList);
		//importRoom(fileNameMap.get("roomList").get(0));
		
		log.info("导入静态数据完成");
	}
	
	/**
	 * 
	 * 导入国家信息
	 */
	private void importCountry(File file, List<HotelCountry> countryList) throws Exception {
		System.out.println("导入国家信息开始");
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
		System.out.println("导入国家信息结束");
	}
	
	
	/**
	 * 
	 * 导入城市信息
	 */
	private void importCity(File file, List<HotelCountry> countryList, List<HotelCity> cityList) throws Exception {
		System.out.println("导入城市信息开始");
		Map<Long, List<HotelCountry>> countryMap = countryList.stream().collect(Collectors.groupingBy(HotelCountry::getCountryId));
		List<String> countryId = Arrays.asList("104,216");
		List<HotelCity> insideCities = new ArrayList<>();
		List<HotelCity> outsideCities = new ArrayList<>();
		File[] dataFiles = file.listFiles();
		for (File dataFile : dataFiles) {
			try (BufferedReader reader = new BufferedReader(new FileReader(dataFile))) {
				String s = null;
				while ((s = reader.readLine()) != null) {
					List<AggrSearch> aggrSearchList = new ArrayList<>();
					ImportCityVo vo = JSON.parseObject(s, ImportCityVo.class);
					log.info("城市数量:{}", vo.getCityList().size());
					vo.getCityList().forEach(item -> {
						item.setNameChn(item.getNameChn().trim());
						List<HotelCountry> list = countryMap.get(item.getCountryId());
						if (!CollectionUtils.isEmpty(list)) {
							item.setCountryNameChn(list.get(0).getNameChn());
							item.setCountryNameEng(list.get(0).getNameEng());
							if (countryId.contains(item.getCountryId().toString())) {
								insideCities.add(item);
							} else {
								outsideCities.add(item);
							}
						}
						aggrSearchList.add(assembleAggrSearchFromCity(item));
					});
					cityList.addAll(vo.getCityList());
					//数据保存es
					aggrSearchRepository.saveAll(aggrSearchList);
				}
			}
		}
		
		List<HotelCityVo> insideCitiesVo = new ArrayList<>();
		List<HotelCityVo> outsideCitiesVo = new ArrayList<>();
		insideCities.stream().forEach(item -> insideCitiesVo.add(new HotelCityVo(item.getCityId(), item.getNameChn(), item.getCountryNameChn())));
		outsideCities.stream().forEach(item -> outsideCitiesVo.add(new HotelCityVo(item.getCityId(), item.getNameChn(), item.getCountryNameChn())));
		
		Map<String, List<HotelCityVo>> insideCityMap = insideCitiesVo.stream().collect(Collectors.groupingBy(item -> String.valueOf(PinYinUtil.getPinYin(item.getNameChn()).charAt(0)).toUpperCase()));
		Map<String, List<HotelCityVo>> outsideCityMap = outsideCitiesVo.stream().collect(Collectors.groupingBy(item -> String.valueOf(PinYinUtil.getPinYin(item.getNameChn()).charAt(0)).toUpperCase()));
		//手动矫正多音字城市
		//fixCityGroup(insideCityMap, insideCitiesVo);
		
		//上传城市文件到七牛云
	}
	
	private void fixCityGroup(Map<String, List<HotelCityVo>> insideCityMap, List<HotelCityVo> insideCitiesVo) {
		transCity("Z", "C", Arrays.asList("长沙", "长春", "长白山", "长治", "重庆"), insideCityMap, insideCitiesVo);
		transCity("S", "X", Arrays.asList("厦门"), insideCityMap, insideCitiesVo);
	}
	
	private void transCity(String originGroup, String targetGroup, List<String> cityName, 
						   Map<String, List<HotelCityVo>> insideCityMap, List<HotelCityVo> cities) {
		try {
			List<HotelCityVo> list = cities.stream().filter(item -> cityName.contains(item.getNameChn())).collect(Collectors.toList());
			insideCityMap.get(targetGroup).addAll(list);
			insideCityMap.get(originGroup).removeAll(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 导入酒店信息
	 */
	private void importHotel(File file, List<HotelCity> cityList) throws Exception {
		Map<Long, List<HotelCity>> cityMap = cityList.stream().collect(Collectors.groupingBy(HotelCity::getCityId));
		File[] dataFiles = file.listFiles();
		for (File dataFile : dataFiles) {
			try (BufferedReader reader = new BufferedReader(new FileReader(dataFile))) {
				String s = null;
				while ((s = reader.readLine()) != null) {
					List<AggrSearch> aggrSearchList = new ArrayList<>();
					List<HotelSearch> hotelSearchList = new ArrayList<>();
					ImportHotelVo vo = JSON.parseObject(s, ImportHotelVo.class);
					//设置酒店最低价
					setMinPrice(vo.getHotelList());
					try {
						vo.getHotelList().forEach(item -> {
							List<HotelCity> city = cityMap.get(item.getCityId());
							aggrSearchList.add(assembleAggrSearchFromHotel(item, city));
							hotelSearchList.add(assembleHotelSearch(item));
						});
						//数据保存es
						aggrSearchRepository.saveAll(aggrSearchList);
					} catch (Exception e) {
						e.printStackTrace();
						log.error("酒店导入aggrSearch失败，文件名:{}", dataFile.getName());
					}
					
					int size = hotelSearchList.size();
					int step = 2000;
					for (int i = 0; i < size; i += step) {
						if (i + step > size) {
							step = size - i;
						}
						log.info("酒店导入ES,数量:{},分组:({},{})", hotelSearchList.size(), i, i +step);
						List<HotelSearch> subList = hotelSearchList.subList(i, i + step);
						try {
							hotelSearchRepository.saveAll(subList);
						} catch (Exception e) {
							e.printStackTrace();
							log.error("酒店导入hotelSearch失败，文件名:{}", dataFile.getName());
						}
						//数据保存mysql
					}
				}
			}
		}
	}
	

	private void setMinPrice(List<HotelInfo> hotelList) {
		hotelList.forEach(item -> {
			item.setMinPrice(BigDecimal.valueOf(new Random().nextInt(100000)));
		});
	}

	/**
	 * 
	 * 导入房型信息
	 */
	/*private void importRoom(File file) throws Exception {
		log.info("导入房型");
		File[] dataFiles = file.listFiles();
		for (File dataFile : dataFiles) {
			try (BufferedReader reader = new BufferedReader(new FileReader(dataFile))) {
				String s = null;
				while ((s = reader.readLine()) != null) {
					ImportRoomVo vo = JSON.parseObject(s, ImportRoomVo.class);
					List<ImportHotelRoomVo> hotelList = vo.getHotelList();
					hotelList.forEach(item -> {
						List<HotelRoom> roomList = item.getRoomList();
						roomList.forEach(room -> {
							room.setCityId(item.getCityId());
							room.setHotelId(item.getHotelId());
						});
						repository.insertBatch(roomList);
					});
				}
			}
		}
	}*/
	
	/*@Transactional
	public void updateDataVersion(Integer versionId, Date currentTime) {
		//更新本次插入数据版本号
		updateVersion(versionId, currentTime);
		//更新版本号启用状态
		HotelDataVersion dataVersion = new HotelDataVersion(versionId, 1);
		repository.updatePatch(dataVersion);
		//删除旧版本数据
		deleteData(versionId);
		//更新redis版本号
		redis.opsForValue().set(CommonConstant.STATIC_DATA_VERSION, String.valueOf(versionId));
	}*/
	
	private AggrSearch assembleAggrSearchFromCity(HotelCity city) {
		AggrSearch search = new AggrSearch();
		search.setId("C" + city.getCityId());
		search.setType(1);
		search.setNameChn(city.getNameChn());
		search.setNameEng(city.getNameEng());
		search.setCityId(city.getCityId());
		search.setCityNameChn(city.getNameChn());
		search.setCityNameEng(city.getNameEng());
		search.setStateNameChn(city.getStateNameChn());
		search.setStateNameEng(city.getStateNameEng());
		search.setCountryNameChn(city.getCountryNameChn());
		search.setCountryNameEng(city.getCountryNameEng());
		return search;
	}
	
	private AggrSearch assembleAggrSearchFromHotel(HotelInfo hotel, List<HotelCity> cityList) {
		AggrSearch search = new AggrSearch();
		search.setId("H" + hotel.getHotelId());
		search.setType(2);
		search.setNameChn(hotel.getNameChn());
		search.setNameEng(hotel.getNameEng());
		search.setCityId(hotel.getCityId());
		search.setHotelId(hotel.getHotelId());
		search.setHotelNameChn(hotel.getNameChn());
		search.setHotelNameEng(hotel.getNameEng());
		
		if (!CollectionUtils.isEmpty(cityList)) {
			HotelCity city = cityList.get(0);
			search.setCityNameChn(city.getNameChn());
			search.setCityNameEng(city.getNameEng());
			search.setStateNameChn(city.getStateNameChn());
			search.setStateNameEng(city.getStateNameEng());
			search.setCountryNameChn(city.getCountryNameChn());
			search.setCountryNameEng(city.getCountryNameEng());
			hotel.setCountryId(city.getCountryId());
		}
		return search;
	}
	
	private HotelSearch assembleHotelSearch(HotelInfo hotel) {
		HotelSearch hotelSearch = hotelSearchRepository.findById(hotel.getHotelId().toString()).orElse(new HotelSearch());
		hotelSearch.setId(hotel.getHotelId().toString());
		hotelSearch.setNameChn(hotel.getNameChn());
		hotelSearch.setNameEng(hotel.getNameEng());
		hotelSearch.setAddress(hotel.getAddress());
		hotelSearch.setAddressEng(hotel.getAddressEng());
		hotelSearch.setLongitude(hotel.getLongitude());
		hotelSearch.setLatitude(hotel.getLatitude());
		hotelSearch.setCityId(hotel.getCityId());
		if (hotel.getMinPrice() != null) {
			hotelSearch.setMinPrice(hotel.getMinPrice().doubleValue());
		}
		hotelSearch.setStar(hotel.getStar());
		hotelSearch.setCommentScore(hotel.getCommentScore());
		hotelSearch.setFacilities(hotel.getFacilities());
		hotelSearch.setImageList(hotel.getImageList());
		hotelSearch.setRemarks(hotel.getRemarks());
		hotelSearch.setIntroduction(hotel.getIntroduction());
		hotelSearch.setUpdateTime(new Date());
		if (hotel.getLatitude() != null && hotel.getLongitude() != null) {
			hotelSearch.setLocation(new GeoPoint(hotel.getLatitude(), hotel.getLongitude()));
		}
		return hotelSearch;
	}
	
	
	
	public static class Ftp {
		
		/** FTP地址 */
		private String url;
		
		/** FTP端口 */
		private Integer port;
		
		/** FTP用户名 */
		private String user;
		
		/** FTP用户密码 */
		private String password;
		
		/** FTP编码 */
		private String charset;
		
		/** 静态数据文件名 */
		private String targetFileName;
		
		/** 本地文件名 */
		private String localFileName;
		
		/** 静态数据-国家文件名 */
		private String countryFile;
		
		/** 静态数据-城市文件名 */
		private String cityFile;
		
		/** 静态数据-酒店文件名 */
		private String hotelFile;
		
		/** 静态数据-房型文件名 */
		private String roomFile;
	}

}


