package com.pakio.demoelasticsearch.SpringDataElasticsearch.client;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class ElasticsearchRestTemplate extends AbstractElasticsearchConfiguration {

  @Value("${spring.elasticsearch.host}")
  private String host;

  @Value("${spring.elasticsearch.port}")
  private Integer port;

  @Value("${spring.elasticsearch.https}")
  private Boolean https;

  @Bean
  RestHighLevelClient client() {
    return getClient();
  }

  private RestHighLevelClient getClient() {
    RestHighLevelClient client = new RestHighLevelClient(
        RestClient.builder(new HttpHost(host, port, https ? "https" : "http"))
            .setHttpClientConfigCallback(httpAsyncClientBuilder -> httpAsyncClientBuilder)
            .setRequestConfigCallback(requestConfigBuilder -> requestConfigBuilder)
    );
    return client;
  }

  @Override
  public RestHighLevelClient elasticsearchClient() {
    return this.getClient();
  }
}
