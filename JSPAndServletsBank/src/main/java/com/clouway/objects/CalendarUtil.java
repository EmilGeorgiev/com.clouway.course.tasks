package com.clouway.objects;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by clouway on 5/30/14.
 */
public class CalendarUtil implements Clock{
  @Override
  public String now() {
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();
    return dateFormat.format(date);
  }
}
