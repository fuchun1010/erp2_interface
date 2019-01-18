package com.datacenter;

import com.datacenter.util.TableUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;

/**
 * @author fuchun
 */
@Slf4j
public class MysqlInit {
  public static void main(String[] args) {
    TableUtil tableUtil = new TableUtil();
    try {
      tableUtil.generateTable();
      //tableUtil.generateMetaData();
    } catch (FileNotFoundException e) {
      log.error(e.getMessage());
    }
  }
}
