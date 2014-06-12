package com.clouway.persistents.util;

import java.util.Calendar;

/**
 * Created by clouway on 6/12/14.
 */
public class CalendarUtil {

  public static long february(int year, int month, int day) {
    Calendar calendar = Calendar.getInstance();


    calendar.set(Calendar.YEAR, year);
    calendar.set(Calendar.MONTH, month);
    calendar.set(Calendar.DAY_OF_MONTH, day);
    calendar.set(Calendar.MILLISECOND, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.HOUR, 0);


    return calendar.getTimeInMillis();
  }
}
