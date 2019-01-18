package com.datacenter.util;

import lombok.val;

import static java.io.File.separator;

/**
 * @author fuchun
 * @date 2019-01-19
 */
public class FileUtil {

  public String locateFilePath(final String filePath) {
    return this.configPath() + filePath;
  }

  private String configPath() {
    val configPath = System.getProperty("user.dir") + separator + "config" + separator;
    return configPath;
  }
}
