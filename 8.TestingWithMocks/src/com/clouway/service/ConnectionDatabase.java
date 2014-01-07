package com.clouway.service;

/**
 * Created by clouway on 12/27/13.
 */
public interface ConnectionDatabase {
  void save(String recipient, String age);

  int receive(String recipient, String age);
}
