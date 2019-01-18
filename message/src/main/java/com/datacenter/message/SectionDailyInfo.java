package com.datacenter.message;

import lombok.Data;

import java.util.List;

/**
 * @author fuchun
 */
@Data
public class SectionDailyInfo {

  private String storeId;

  private String storeCode;

  private List<SectionSalesDate> salesDate;
}
