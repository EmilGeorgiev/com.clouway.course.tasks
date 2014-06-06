package com.clouway.action.calendarutils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by clouway on 5/30/14.
 */
public class CalendarUtil {
  public static Date may(int year, int month, int day) {

    Calendar calendar = Calendar.getInstance();

    calendar.set(Calendar.YEAR, year);
    calendar.set(Calendar.MONTH, month - 1);
    calendar.set(Calendar.DAY_OF_MONTH, day);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);

    return calendar.getTime();
  }
}
