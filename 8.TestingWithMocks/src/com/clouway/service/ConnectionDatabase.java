package com.clouway.service;

/**
 * Created by clouway on 12/27/13.
 */
public interface ConnectionDatabase {
  void save(String name, String age);

  int receive(String name, String age);
}
