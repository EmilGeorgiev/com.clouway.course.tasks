package com.clouway.service;

/**
 * Created by clouway on 12/27/13.
 */
public class Service {
  private final Validator validator;
  private final Database database;
  private final int minAge;
  private final int lawfulAge;

  public Service(Validator validator, Database database, AgeRestriction ageRestriction) {
    this.validator = validator;
    this.database = database;
    this.minAge = ageRestriction.getMinAge();
    this.lawfulAge = ageRestriction.getLawfulAge();
  }

  /**
   * Save the years in the database if they are between 10 and 100
   * @param person person that will adding.
   */
  public void savePersonInDataBase(Person person) {
    validator.validateYears(person.getAge(), minAge);
    database.save(person);
  }

  /**
   * Take years from the database if they are valid
   * @param person person it will take years from the database if they are valid.
   * @return years of the recipient.
   */
  public int getYearsOfPersonFromDatabase(Person person) {
    validator.validateYears(person.getAge(), lawfulAge);
    return database.receive(person.getAge());
  }
}
