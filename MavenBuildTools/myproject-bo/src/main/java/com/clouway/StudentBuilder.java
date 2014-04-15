package com.clouway;

/**
 * Created by clouway on 4/14/14.
 */
public class StudentBuilder {

  private String firstName;
  private String lastName;
  private int age;
  private int idNumber;

  public static StudentBuilder instanceStudentBuilder() {
    return new StudentBuilder();
  }

  public StudentBuilder firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public StudentBuilder lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public StudentBuilder age(int age) {
    this.age = age;
    return this;
  }

  public StudentBuilder idNumber(int idNumber) {
    this.idNumber = idNumber;
    return this;
  }

  public Student build() {
    return new Student(firstName, lastName, age, idNumber);
  }
}
