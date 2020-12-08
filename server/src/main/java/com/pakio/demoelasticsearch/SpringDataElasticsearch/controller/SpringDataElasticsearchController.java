package com.pakio.demoelasticsearch.SpringDataElasticsearch.controller;

import com.pakio.demoelasticsearch.SpringDataElasticsearch.entity.DestIndex;
import com.pakio.demoelasticsearch.SpringDataElasticsearch.service.SpringDataElasticsearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

  @RequestMapping("/index-document")
  public Boolean indexDocument(@RequestParam("id") Integer id,
      @RequestParam("message") String message) {
    return springDataElasticsearchService.indexDocument(id, message);
  }

  @RequestMapping("/get-document")
  public ResponseEntity<DestIndex> getDocument(@RequestParam("id") Integer id) {
    DestIndex doc = springDataElasticsearchService.getDocumentById(id);
    return new ResponseEntity<DestIndex>(doc, HttpStatus.OK);
  }

  @RequestMapping("/get-document-with-query")
  public ResponseEntity<DestIndex> getDocumentWithQuery(@RequestParam("id") Integer id) {
    DestIndex doc = springDataElasticsearchService.getDocumentByIdWithQuery(id);
    return new ResponseEntity<DestIndex>(doc, HttpStatus.OK);
  }
}
