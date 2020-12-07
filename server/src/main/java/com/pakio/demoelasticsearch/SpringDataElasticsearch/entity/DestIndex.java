package com.pakio.demoelasticsearch.SpringDataElasticsearch.entity;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "dest_index2")
public class DestIndex {
  @Field(type= FieldType.Text)
  String message;

  public String getMessage() {
    return this.message;
  }
}
