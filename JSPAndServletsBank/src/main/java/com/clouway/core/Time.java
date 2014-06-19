package com.clouway.core;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by clouway on 6/16/14.
 */
public class Time implements Clock {

  @Override
  public java.util.Date now() {

    Calendar calendar = Calendar.getInstance();
    return new Timestamp(calendar.getTimeInMillis());
  }
}
