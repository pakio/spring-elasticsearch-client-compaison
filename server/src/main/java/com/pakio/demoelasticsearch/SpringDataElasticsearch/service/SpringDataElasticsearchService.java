package com.pakio.demoelasticsearch.SpringDataElasticsearch.service;

import com.pakio.demoelasticsearch.SpringDataElasticsearch.entity.DestIndex;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.query.StringQuery;
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

  public Boolean indexDocument(Integer id, String message) {
    DestIndex destIndex = new DestIndex();
    destIndex.setId(id.toString());
    destIndex.setMessage(message);

    try {
      this.elasticsearchOperations.save(destIndex);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public DestIndex getDocumentById(Integer id) {
    return this.elasticsearchOperations.get(id.toString(), DestIndex.class);
  }

  public DestIndex getDocumentByIdWithQuery(Integer id) {
    StringQuery query = new StringQuery(QueryBuilders.termQuery("_id", id).toString());

    return this.elasticsearchOperations.searchOne(query, DestIndex.class).getContent();
  }
}
