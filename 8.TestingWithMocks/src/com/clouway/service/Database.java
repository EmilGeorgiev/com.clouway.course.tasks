package com.clouway.service;

/**
 * Created by clouway on 12/27/13.
 */
public interface Database {
  void save(Person person);

  int receive(int age);
}
