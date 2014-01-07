package com.clouway.service;

/**
 * Created by clouway on 1/7/14.
 */
public class Person {
  private final String name;
  private final int age;

  public Person(String name, int age) {

    this.name = name;
    this.age = age;
  }

  public int getAge() {
    return age;
  }
}
