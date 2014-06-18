package com.clouway.core;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by clouway on 6/16/14.
 */
public class Time implements Clock {

  private int timeExpiry;

  public void setTimeExpiry(int timeExpiry) {
    this.timeExpiry = timeExpiry;
  }

  @Override
  public Timestamp now() {

    Calendar calendar = Calendar.getInstance();
    return new Timestamp(calendar.getTimeInMillis());
  }

  @Override
  public int getExpiryTime() {
    return this.timeExpiry;
  }
}
