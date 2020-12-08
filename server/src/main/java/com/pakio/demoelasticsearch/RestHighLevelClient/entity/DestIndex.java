package com.pakio.demoelasticsearch.RestHighLevelClient.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class DestIndex {

  @JsonProperty("id")
  Integer id;

  @JsonProperty("message")
  String message;
}
