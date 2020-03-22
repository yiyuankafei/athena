package com.athena.search.es.application.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.athena.search.es.application.document.HaoqiaoDocument;

public interface HaoqiaoRepository extends ElasticsearchRepository<HaoqiaoDocument, String> {

}
