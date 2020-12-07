package com.pakio.demoelasticsearch.RestHighLevelClient.controller;

import com.pakio.demoelasticsearch.RestHighLevelClient.service.RestHighLevelClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
