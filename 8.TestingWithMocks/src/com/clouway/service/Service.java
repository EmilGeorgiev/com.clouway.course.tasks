package com.clouway.service;

/**
 * Created by clouway on 12/27/13.
 */
public class Service {
  private final Validator validator;
  private final ConnectionDatabase connect;

  public Service(Validator validator, ConnectionDatabase connect) {
    this.validator = validator;
    this.connect = connect;
  }

  public void saveAge(String name, String age) {
    if (!validator.validateAge(age)) {
      throw new IllegalArgumentException("Years should be 10 to 100!");
    }
    connect.save(name, age);
  }

  public boolean getAge(String name, String age) {
    if(!validator.validateGetAge(age)) {
      return false;
    }
    connect.receive(name, age);
    return true;
  }
}
