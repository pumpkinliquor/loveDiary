package com.plushih.common.utils;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * Created by sangyong on 10/21/14.
 */
public class JsonUtils {

  private static ObjectMapper jsonMapper = new ObjectMapper();

  public static <T> void objectToJsonFile(final T object, final String filepath) {
  }

  public static <T> String objectToJsonString(final T object) throws IOException {
    return jsonMapper.writeValueAsString(object);
  }

  public static Object jsonStringToObject(final String jsonString, Class<?> clazz) throws IOException {
    return jsonMapper.readValue(jsonString, clazz);
  }
}
