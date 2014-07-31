package com.clouway.core;

import java.util.Calendar;
import java.util.Date;

/**
 * Ensure current date and time.
 */
public class Time implements Clock {

  @Override
  public Date now() {
    return Calendar.getInstance().getTime();
  }
}
