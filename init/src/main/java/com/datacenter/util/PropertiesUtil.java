package com.datacenter.util;

import com.google.common.collect.Maps;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

/**
 * @author fuchun
 */
public class PropertiesUtil {

  /**
   *
   * @param fileName
   * @return
   */
  public Map<String, String> loadProperties(final String fileName) {
    Map<String, String> props = Maps.newHashMap();
    FileUtil fileUtil = new FileUtil();
    String path = fileUtil.locateFilePath(fileName);
    File file = new File(path);
    Properties properties = new Properties();
    try {
      properties.load(new FileInputStream(file));
      Enumeration keys = properties.propertyNames();
      while (keys.hasMoreElements()) {
        String key = (String) keys.nextElement();
        String value = properties.getProperty(key);
        props.putIfAbsent(key, value);
      }
    } catch (IOException e) {
      System.out.println("exception:" + e.getMessage());
    }
    return props;
  }

}
