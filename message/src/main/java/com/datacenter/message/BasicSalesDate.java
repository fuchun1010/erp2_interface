package com.datacenter.message;

import lombok.Data;

/**
 * @author fuchun
 * @date 2019-01-17
 */
@Data
public class BasicSalesDate {

  private int day;

  private String date;

  private int shiftType;

  private int holidayFlag;

  private double salsAmtProp;

  private double guestQtyProp;
}
