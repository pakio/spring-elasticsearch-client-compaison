package com.pakio.demoelasticsearch.SpringDataElasticsearch.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "dest_index2")
public class DestIndex {

  @Id
  String id;

  @Field(type = FieldType.Text)
  String message;
}
