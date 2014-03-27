package com.clouway.xmlmessage;

/**
 * Created by clouway on 3/25/14.
 */
public class EmployeeBuilder {

  private String firstName;
  private String lastName;
  private int age;
  private String position;
  private Address address;
  private Employer employer;

  public static EmployeeBuilder newInstance() {
    return new EmployeeBuilder();
  }

  public EmployeeBuilder firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public EmployeeBuilder lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public EmployeeBuilder age(int age) {
    this.age = age;
    return this;
  }

  public EmployeeBuilder position(String position) {
    this.position = position;
    return this;
  }

  public EmployeeBuilder address(Address address) {
    this.address = address;
    return this;
  }

  public EmployeeBuilder employer(Employer employer) {
    this.employer = employer;
    return this;
  }

  public Employee build() {
    return new Employee(address, employer, firstName, lastName, age, position);
  }
}
