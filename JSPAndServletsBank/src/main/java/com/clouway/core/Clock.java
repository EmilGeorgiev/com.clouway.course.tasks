package com.clouway.core;

import java.sql.Timestamp;


public interface Clock {

  Timestamp now();

  int getExpiryTime();

  void setTimeExpiry(int timeExpiry);
}
