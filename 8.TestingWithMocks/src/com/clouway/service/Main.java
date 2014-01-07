package com.clouway.service;

/**
 * Created by clouway on 1/7/14.
 */
public class Main {
  public static void main(String[] args) {
    AgeValidator validator = new AgeValidator(10, 100, 18);
    Database database = new Database() {
      @Override
      public void save(String age) {

      }

      @Override
      public int receive(String age) {
        int result = Integer.parseInt(age);
        return result;
      }
    };
    Service service = new Service(validator, database);
    service.saveAge("15");
    service.saveAge("5");
    service.getAge("15");

  }
}
