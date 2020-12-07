package com.pakio.demoelasticsearch.RestHighLevelClient.service;

import com.pakio.demoelasticsearch.RestHighLevelClient.client.ClientRepository;
import com.pakio.demoelasticsearch.RestHighLevelClient.entity.IndexMapping;
import java.util.Map;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestHighLevelClientService {
  @Autowired
  ClientRepository clientRepository;

  public String createIndex() {
    Map mapping = IndexMapping.getIndexMapping();
    CreateIndexRequest request = new CreateIndexRequest("dest_index");
    request.source(mapping);
    if (clientRepository.createIndex(request).isAcknowledged()) {
      return "ok";
    } else {
      return "ng";
    }
  }
}
