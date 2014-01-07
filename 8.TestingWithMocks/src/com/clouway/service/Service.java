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

  /**
   * Save the years in the database if they are between 10 and 100
   * @param recipient is recipient of the message.
   * @param age year of the beneficiary
   */
  public void saveAge(String recipient, String age) {
    if (!validator.validateAge(age)) {
      throw new IllegalArgumentException("Years should be 10 to 100!");
    }
    connect.save(recipient, age);
  }

  /**
   * Take years from the database if they are valid
   * @param recipient
   * @param age
   * @return years of the recipient.
   */
  public int getAge(String recipient, String age) {
    if(!validator.validateGetAge(age)) {
      throw new IllegalArgumentException("Years may be from 18 to 100");
    }

    return connect.receive(recipient, age);
  }
}
