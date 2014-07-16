package com.clouway.core;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by clouway on 7/15/14.
 */
public class Time implements Clock {
  @Override
  public Date now() {
    return Calendar.getInstance().getTime();
  }
}
