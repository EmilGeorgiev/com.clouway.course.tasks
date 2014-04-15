package com.clouway;

/**
 * Created by clouway on 4/14/14.
 */
public class Room {
  private Student student;
  private final int number;

  public Room(Student student, int number) {
    this.student = student;
    this.number = number;
  }

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  public int getNumber() {
    return number;
  }
}
