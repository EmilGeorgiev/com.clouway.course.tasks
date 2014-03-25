package com.clouway.travel;

/**
 * Created by clouway on 3/19/14.
 */
public class PersonBuilder {

  /**
   * Creates a new PersonBuilder for building of a new Person.
   * @return the newly created {@link com.clouway.travel.PersonBuilder}
   */
  public static PersonBuilder newPerson() {
    return new PersonBuilder();
  }

  private int age;
  private String name;
  private String eMail;
  private String egn;


  public PersonBuilder age(int age) {
    this.age = age;
    return this;
  }

  public PersonBuilder name(String name) {
    this.name = name;
    return this;
  }

  public PersonBuilder eMail(String eMail) {
    this.eMail= eMail;
    return this;
  }

  public PersonBuilder egn(String egn) {
    this.egn = egn;
    return this;
  }

  public Person build() {
    return new Person(egn, name, age, eMail);
  }


}
