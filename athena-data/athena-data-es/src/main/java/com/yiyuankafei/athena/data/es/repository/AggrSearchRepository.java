package com.yiyuankafei.athena.data.es.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.yiyuankafei.athena.data.es.document.AggrSearch;

public interface AggrSearchRepository extends ElasticsearchRepository<AggrSearch, String> {

}
