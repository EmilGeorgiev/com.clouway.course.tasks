package com.clouway;


public class Student {
  private String firstName;
  private String lastName;
  private int age;
  private int idNumber;

  public Student() {
  }

  public Student(String firstName, String lastName, int age, int idNumber) {

    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    this.idNumber = idNumber;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public int getAge() {
    return age;
  }

  public int getIdNumber() {
    return idNumber;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Student student = (Student) o;

    if (age != student.age) return false;
    if (idNumber != student.idNumber) return false;
    if (firstName != null ? !firstName.equals(student.firstName) : student.firstName != null) return false;
    if (lastName != null ? !lastName.equals(student.lastName) : student.lastName != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = firstName != null ? firstName.hashCode() : 0;
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    result = 31 * result + age;
    result = 31 * result + idNumber;
    return result;
  }
}
