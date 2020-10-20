package com.yiyuankafei.athena.data.es.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.yiyuankafei.athena.data.es.document.HotelSearch;


public interface HotelRepository extends ElasticsearchRepository<HotelSearch, String> {


}
