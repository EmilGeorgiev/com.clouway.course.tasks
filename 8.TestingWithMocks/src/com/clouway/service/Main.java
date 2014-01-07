package com.clouway.service;

/**
 * Created by clouway on 1/7/14.
 */
public class Main {
  public static void main(String[] args) {
    AgeValidator validator = new AgeValidator(10, 100, 18);
    Database database = new Database() {
      @Override
      public void save(Person person) {

      }

      @Override
      public int receive(int age) {
        return age;
      }
    };
    Person person = new Person("Emil", 15);
    Person person2 = new Person("Petyr", 5);

    Service service = new Service(validator, database);
    service.savePersonInDataBase(person);
    service.savePersonInDataBase(person2);
    service.getYearsOfPersonFromDatabase(person);

  }
}
