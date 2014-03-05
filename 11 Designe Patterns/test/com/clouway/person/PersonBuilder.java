package com.clouway.person;

/**
 * Created by clouway on 2/24/14.
 */
public class PersonBuilder {

  /**
   * Creates a new PersonBuilder for building of a new Person.
   * @return the newly created {@link com.clouway.person.PersonBuilder}
   */
  public static PersonBuilder newPerson() {
    return new PersonBuilder();
  }


  private int age;
  private String name;

  public PersonBuilder age(int age) {
    this.age = age;
    return this;
  }

  public PersonBuilder named(String name) {
    this.name = name;
    return this;
  }


  public Person build() {
    return new Person(name,age,"","","");
  }
}
