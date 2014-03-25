package com.clouway.travel;

/**
 * Created by clouway on 3/17/14.
 */
public class Person {
  private final String egn;
  private final String name;
  private final int age;
  private final String eMail;

  public Person(String egn, String name, int age, String eMail) {

    this.egn = egn;
    this.name = name;
    this.age = age;
    this.eMail = eMail;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Person person = (Person) o;

    if (age != person.age) return false;
    if (eMail != null ? !eMail.equals(person.eMail) : person.eMail != null) return false;
    if (egn != null ? !egn.equals(person.egn) : person.egn != null) return false;
    if (name != null ? !name.equals(person.name) : person.name != null) return false;

    return true;
  }


  public String getEgn() {
    return egn;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public String getEmail() {
    return eMail;
  }
}
