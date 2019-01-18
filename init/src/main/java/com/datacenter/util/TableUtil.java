package com.datacenter.util;

import com.datacenter.service.MysqlService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.io.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author fuchun
 * @date 2019-01-19
 */
@Slf4j
public class TableUtil {


  public void generateTable() throws FileNotFoundException {
    val sqlService = new MysqlService();
    val properties = new PropertiesUtil();
    final Map<String, String> props = properties.loadProperties("mysql.properties");
    final String prefix = props.get("prefix");
    Objects.requireNonNull(prefix);
    this.handleStoreCode("stores.csv", storeCode -> {
      String[] table_prefix = prefix.split(",");
      //TODO 补全table shecma
      int hashCode = Math.floorMod(Math.abs(Objects.hash(storeCode)), 100);
      List<String> statement = Arrays.stream(table_prefix)
          .map(p -> String.format("create table if not exists tab_%s_%d ()", p, hashCode))
          .collect(Collectors.toList());

      for (String sql : statement) {
        sqlService.createTable(sql);
      }


    });

  }

  public void generateMetaData() throws FileNotFoundException {
    List<String> values = Lists.newLinkedList();
    MysqlService mysqlService = new MysqlService();
    this.handleStoreCode("stores.csv", storeCode -> {
      val hashCode = Math.floorMod(Math.abs(Objects.hashCode(storeCode)), 100);
      val value = String.format("('%s',%d)", storeCode, hashCode);
      values.add(value);
      if (values.size() == 100) {
        String insertSql = this.composeInsertSql(values);
        mysqlService.executesql(insertSql);
      }
    });

    if (values.size() > 0) {
      String insertSql = this.composeInsertSql(values);
      mysqlService.executesql(insertSql);
    }
  }


  private void handleStoreCode(final String fileName, Consumer<String> fun) throws FileNotFoundException {
    val fileUtil = new FileUtil();
    val filePath = fileUtil.locateFilePath(fileName);
    val file = new File(filePath);
    if (!file.exists()) {
      throw new FileNotFoundException(fileName + " not exists");
    }
    BufferedReader in = null;
    try {
      in = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
      Objects.requireNonNull(in);
      while (true) {
        final String storeCode = in.readLine();
        if (Objects.isNull(storeCode)) {
          break;
        }
        fun.accept(storeCode);
      }
    } catch (Exception e) {
      log.error(e.getMessage());
    } finally {
      try {
        in.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private String composeInsertSql(final List<String> values) {

    val insertSql = "insert into tab_meta values ";
    StringJoiner joiner = new StringJoiner(",");
    values.forEach(joiner::add);
    val sql = insertSql + joiner.toString();
    System.out.println("sql = " + sql);
    values.clear();
    return sql;
  }


}
