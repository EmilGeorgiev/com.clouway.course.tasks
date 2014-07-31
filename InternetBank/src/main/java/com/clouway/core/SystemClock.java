package com.clouway.core;

import java.util.Date;

/**
 * Ensure current date and time.
 */
public class SystemClock implements Clock {

  @Override
  public Date now() {
    return new Date();
  }
}
