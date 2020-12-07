package com.pakio.demoelasticsearch.RestHighLevelClient.entity;

import java.util.HashMap;
import java.util.Map;

public class IndexMapping {
  public static Map getIndexMapping() {
    Map<String, Object> jsonMap = new HashMap<>();
    Map<String, Object> message = new HashMap<>();
    message.put("type", "text");
    Map<String, Object> properties = new HashMap<>();
    properties.put("message", message);
    jsonMap.put("properties", properties);

    return jsonMap;
  }
}
