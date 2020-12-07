package com.pakio.demoelasticsearch.SpringDataElasticsearch.controller;

import com.pakio.demoelasticsearch.SpringDataElasticsearch.service.SpringDataElasticsearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spring-data-elasticsearch")
public class SpringDataElasticsearchController {
  @Autowired
  SpringDataElasticsearchService springDataElasticsearchService;

  @RequestMapping("/create-index")
  public String createIndex() {
    return springDataElasticsearchService.createIndex().toString();
  }

}
