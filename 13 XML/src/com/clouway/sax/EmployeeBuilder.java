package com.clouway.sax;

/**
 * Created by clouway on 4/2/14.
 */
public class EmployeeBuilder {

  private String name;
  private int age;
  private Address address;
  private Employer employer;

  public static EmployeeBuilder newEmployee() {
    return new EmployeeBuilder();
  }

  public EmployeeBuilder name(String name) {
    this.name = name;
    return this;
  }

  public EmployeeBuilder age(int age) {
    this.age = age;
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
    return new Employee(name, age, address, employer);
  }


}
