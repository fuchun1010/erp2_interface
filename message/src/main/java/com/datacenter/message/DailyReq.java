package com.datacenter.message;

import lombok.Data;

import java.util.List;

/**
 * @author fuchun
 */
@Data
public class DailyReq {

  private String date;

  private List<String> stores;
}

