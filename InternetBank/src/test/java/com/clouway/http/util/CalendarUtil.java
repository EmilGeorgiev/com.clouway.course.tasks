package com.clouway.http.util;

import com.clouway.core.Clock;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by clouway on 7/14/14.
 */
public class CalendarUtil implements Clock {

  private final int year;
  private final int month;
  private final int day;
  private final int hour;
  private final int minute;

  public CalendarUtil(int year, int month, int day, int hour, int minute) {
    this.year = year;
    this.month = month;
    this.day = day;
    this.hour = hour;
    this.minute = minute;
  }

  @Override
  public Date now() {
    Calendar calendar = Calendar.getInstance();

    calendar.set(Calendar.YEAR, year);
    calendar.set(Calendar.MONTH, month);
    calendar.set(Calendar.DAY_OF_MONTH, day);
    calendar.set(Calendar.HOUR_OF_DAY, hour);
    calendar.set(Calendar.MINUTE, minute);

    return calendar.getTime();
  }
}
