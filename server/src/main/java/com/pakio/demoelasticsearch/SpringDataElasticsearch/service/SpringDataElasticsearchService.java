package com.pakio.demoelasticsearch.SpringDataElasticsearch.service;

import com.pakio.demoelasticsearch.SpringDataElasticsearch.entity.DestIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.stereotype.Service;

@Service
public class SpringDataElasticsearchService {
  private final ElasticsearchOperations elasticsearchOperations;

  @Autowired
  public SpringDataElasticsearchService(ElasticsearchOperations elasticsearchOperations) {
    this.elasticsearchOperations = elasticsearchOperations;
  }

  public Boolean createIndex() {
    IndexOperations operations = this.elasticsearchOperations.indexOps(DestIndex.class);
    return operations.create();
  }
}
