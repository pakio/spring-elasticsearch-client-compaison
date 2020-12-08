package com.pakio.demoelasticsearch.RestHighLevelClient.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pakio.demoelasticsearch.RestHighLevelClient.client.ClientRepository;
import com.pakio.demoelasticsearch.RestHighLevelClient.entity.DestIndex;
import com.pakio.demoelasticsearch.RestHighLevelClient.entity.IndexMapping;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestHighLevelClientService {

  private final String indexName = "dest_index";
  @Autowired
  ClientRepository clientRepository;

  public static DestIndex convertSearchHitToEntity(SearchHit searchHit) {
    String jsonString = searchHit.getSourceAsString();

    ObjectMapper mapper = new ObjectMapper();
    try {
      DestIndex destIndex = mapper.readValue(jsonString, DestIndex.class);

      return destIndex;
    } catch (Exception e) {
      return new DestIndex();
    }
  }

  public String createIndex() {
    Map mapping = IndexMapping.getIndexMapping();
    CreateIndexRequest request = new CreateIndexRequest(indexName);
    request.source(mapping);
    if (clientRepository.createIndex(request).isAcknowledged()) {
      return "ok";
    } else {
      return "ng";
    }
  }

  /**
   * CREATE
   **/
  public Boolean indexDocument(Integer id, String message) {

    IndexRequest request = new IndexRequest(indexName)
        .id(id.toString())
        .source("message", message);
    return clientRepository.indexDocument(request);
  }

  public Boolean indexDocument(DestIndex destIndex) {
    ObjectMapper mapper = new ObjectMapper();

    try {
      IndexRequest request = new IndexRequest(indexName).id(destIndex.getId().toString())
          .source(mapper.writeValueAsString(destIndex), XContentType.JSON);

      return clientRepository.indexDocument(request);
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * READ
   **/
  public DestIndex getDocumentById(Integer id) {
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

    TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("_id", id);
    searchSourceBuilder.query(termQueryBuilder);

    SearchRequest request = new SearchRequest(indexName);
    request.source(searchSourceBuilder);

    SearchResponse response = clientRepository.getDocument(request);

    SearchHit[] searchHits = response.getHits().getHits();
    Optional<SearchHit> optionalSearchHit = Arrays.stream(searchHits).findFirst();

    if (optionalSearchHit.isPresent()) {
      return convertSearchHitToEntity(optionalSearchHit.get());
    } else {
      return new DestIndex();
    }
  }
}
