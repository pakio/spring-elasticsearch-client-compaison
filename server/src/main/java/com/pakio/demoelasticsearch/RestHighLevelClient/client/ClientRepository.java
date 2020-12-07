package com.pakio.demoelasticsearch.RestHighLevelClient.client;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.PutMappingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClientRepository {
  @Autowired
  private RestHighLevelClientConfig restHighLevelClientConfig;

  private RestHighLevelClient restHighLevelClient;

  @Autowired
  public void setClient(RestHighLevelClient restHighLevelClient) {
    this.restHighLevelClient = restHighLevelClient;
  }

  public SearchResponse getSearchResult(SearchRequest request) {
    try {
      return restHighLevelClient.search(request, RequestOptions.DEFAULT);
    } catch (Exception e) {
      this.setClient(restHighLevelClientConfig.getRecreateClient());
      return null;
    }
  }

  public AcknowledgedResponse createIndex(CreateIndexRequest request) {
    try {
      return restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
    } catch (Exception e) {
      System.out.println(e.getCause());
      System.out.println(e.getMessage());
      this.setClient(restHighLevelClientConfig.getRecreateClient());
      return null;
    }
  }
}
