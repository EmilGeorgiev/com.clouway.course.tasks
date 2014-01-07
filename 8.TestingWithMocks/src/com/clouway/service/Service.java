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
   * @param person person that will adding.
   */
  public void savePersonInDataBase(Person person) {
    validator.validatesTheYearsByAdding(person.getAge());
    connect.save(person);
  }

  /**
   * Take years from the database if they are valid
   * @param person person it will take years from the database if they are valid.
   * @return years of the recipient.
   */
  public int getYearsOfPersonFromDatabase(Person person) {
    validator.validatesTheYearsByGetting(person.getAge());
    return connect.receive(person.getAge());
  }
}
