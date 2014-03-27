package com.clouway.xmlmessage;

/**
 * Created by clouway on 3/25/14.
 */
public class Employee {

  private  Employer employer;
  private  Address address;
  private  String firstName;
  private  String lastName;
  private  int age;
  private  String position;

  public Employee(Address address, Employer employer, String firstName, String lastName, int age, String position) {

    this.employer = employer;
    this.address = address;
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    this.position = position;
  }

  public Employer getEmployee() {
    return employer;
  }

  public void setEmployee(Employer employee) {
    this.employer = employee;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }
}
