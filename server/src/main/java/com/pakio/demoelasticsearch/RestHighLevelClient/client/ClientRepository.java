package com.pakio.demoelasticsearch.RestHighLevelClient.client;

import org.elasticsearch.action.DocWriteResponse.Result;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
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

  public Boolean indexDocument(IndexRequest request) {
    try {
      IndexResponse response = restHighLevelClient.index(request, RequestOptions.DEFAULT);
      return response.getResult() == Result.CREATED;
    } catch (Exception e) {

      return false;
    }
  }

  public SearchResponse getDocument(SearchRequest request) {
    try {
      return restHighLevelClient.search(request, RequestOptions.DEFAULT);
    } catch (Exception e) {
      this.setClient(restHighLevelClientConfig.getRecreateClient());
      return null;
    }
  }
}
