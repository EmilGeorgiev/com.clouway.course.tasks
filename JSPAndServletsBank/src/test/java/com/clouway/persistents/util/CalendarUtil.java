package com.clouway.persistents.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by clouway on 6/12/14.
 */
public class CalendarUtil {

  public static Date getDate(int year, int month, int day, int hour, int minute) {
    Calendar calendar = Calendar.getInstance();


    calendar.set(Calendar.YEAR, year);
    calendar.set(Calendar.MONTH, month);
    calendar.set(Calendar.DAY_OF_MONTH, day);
    calendar.set(Calendar.MILLISECOND, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MINUTE, minute);
    calendar.set(Calendar.HOUR, hour);


    return calendar.getTime();
  }
}
