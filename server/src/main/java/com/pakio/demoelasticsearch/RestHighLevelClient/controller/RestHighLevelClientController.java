package com.pakio.demoelasticsearch.RestHighLevelClient.controller;

import com.pakio.demoelasticsearch.RestHighLevelClient.entity.DestIndex;
import com.pakio.demoelasticsearch.RestHighLevelClient.service.RestHighLevelClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest-high-level-client")
public class RestHighLevelClientController {

  @Autowired
  RestHighLevelClientService restHighLevelClientService;

  @RequestMapping("/create-index")
  public String createIndex() {
    return restHighLevelClientService.createIndex();
  }

  @RequestMapping("/index-document")
  public Boolean indexDocument(@RequestParam("id") Integer id,
      @RequestParam("message") String message) {
    return restHighLevelClientService.indexDocument(id, message);
  }

  @RequestMapping("/index-document-as-entity")
  public Boolean indexDocumentAsEntity(@RequestParam("id") Integer id,
      @RequestParam("message") String message) {
    DestIndex index = new DestIndex();
    index.setMessage(message);
    index.setId(id);
    return restHighLevelClientService.indexDocument(index);
  }

  @RequestMapping("/get-document")
  public ResponseEntity<DestIndex> getDocument(@RequestParam("id") Integer id) {
    DestIndex doc = restHighLevelClientService.getDocumentById(id);
    return new ResponseEntity<DestIndex>(doc, HttpStatus.OK);
  }
}
