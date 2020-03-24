package com.athena.search.es.application.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.athena.search.es.application.document.HotelDocument;

public interface HotelRepository extends ElasticsearchRepository<HotelDocument, String> {

}
