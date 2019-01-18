package com.datacenter.service;

import com.datacenter.util.PropertiesUtil;

import java.sql.*;
import java.util.Map;
import java.util.Objects;

/**
 * @author fuchun
 */
public class MysqlService {

  /**
   * @param sql
   * @return
   */
  public boolean executesql(final String sql) {

    PropertiesUtil propertiesUtil = new PropertiesUtil();
    Map<String, String> props = propertiesUtil.loadProperties("mysql.properties");
    String driverClass = props.get("driverclass");
    String url = props.get("url");
    String username = props.get("username");
    String password = props.get("password");
    Connection conn = null;
    PreparedStatement pst = null;
    boolean successful = false;
    try {
      Class.forName(driverClass);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    try {
      conn = DriverManager.getConnection(url, username, password);
      Objects.requireNonNull(conn);
      pst = conn.prepareStatement(sql);
      Objects.requireNonNull(pst);
      successful = pst.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      if (!Objects.isNull(pst)) {
        try {
          pst.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
      if (!Objects.isNull(conn)) {
        try {
          conn.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }

    return successful;
  }

  public boolean createTable(final String sql) {

    PropertiesUtil propertiesUtil = new PropertiesUtil();
    Map<String, String> props = propertiesUtil.loadProperties("mysql.properties");
    String driverClass = props.get("driverclass");
    String url = props.get("url");
    String username = props.get("username");
    String password = props.get("password");
    Connection conn = null;
    Statement pst = null;
    boolean successful = false;
    try {
      Class.forName(driverClass);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    try {
      conn = DriverManager.getConnection(url, username, password);
      Objects.requireNonNull(conn);
      pst = conn.createStatement();
      successful = pst.execute(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      if (!Objects.isNull(pst)) {
        try {
          pst.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
      if (!Objects.isNull(conn)) {
        try {
          conn.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }

    return successful;
  }

}
