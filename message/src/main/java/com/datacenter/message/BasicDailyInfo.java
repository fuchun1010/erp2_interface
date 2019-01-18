package com.datacenter.message;

import lombok.Data;

import java.util.List;

/**
 * @author fuchun
 * @date 2019-01-17
 */
@Data
public class BasicDailyInfo {

  private String storeId;

  private String storeCode;

  private List<BasicSalesDate> salesDate;
}
