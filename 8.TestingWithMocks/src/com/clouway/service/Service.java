package com.clouway.service;

/**
 * Created by clouway on 12/27/13.
 */
public class Service {
  private final Validator validator;
  private final Database connect;

  public Service(Validator validator, Database connect) {
    this.validator = validator;
    this.connect = connect;
  }

  /**
   * Save the years in the database if they are between 10 and 100
   * @param age year of the beneficiary
   */
  public void saveAge(String age) {
    validator.validatesTheYearsByAdding(age);
    connect.save(age);
  }

  /**
   * Take years from the database if they are valid
   * @param age years it will take from the database if they are valid.
   * @return years of the recipient.
   */
  public int getAge(String age) {
    validator.validatesTheYearsByGetting(age);
    return connect.receive(age);
  }
}
