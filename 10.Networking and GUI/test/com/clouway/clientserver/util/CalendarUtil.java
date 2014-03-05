package com.clouway.clientserver.util;

import java.util.Calendar;
import java.util.Date;

/**
* Created by clouway on 2/12/14.
*/
public class CalendarUtil {

  public static Date february(int year, int day) {
    Calendar calendar = Calendar.getInstance();

    calendar.set(Calendar.YEAR, year);
    calendar.set(Calendar.DAY_OF_MONTH, day);

    return calendar.getTime();
  }
}
