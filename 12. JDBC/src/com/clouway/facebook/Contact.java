package com.clouway.facebook;

/**
 * Created by clouway on 3/21/14.
 */
public class Contact {
  private final int id;
  private final String firstName;
  private final String lastName;
  private final int age;
  private final String eMail;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Contact contact = (Contact) o;

    if (age != contact.age) return false;
    if (id != contact.id) return false;
    if (eMail != null ? !eMail.equals(contact.eMail) : contact.eMail != null) return false;
    if (firstName != null ? !firstName.equals(contact.firstName) : contact.firstName != null) return false;
    if (lastName != null ? !lastName.equals(contact.lastName) : contact.lastName != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    result = 31 * result + age;
    result = 31 * result + (eMail != null ? eMail.hashCode() : 0);
    return result;
  }

  public int getId() {

    return id;
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

  public String getEmail() {
    return eMail;
  }

  public Contact(int id, String firstName, String lastName, int age, String eMail) {

    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    this.eMail = eMail;
  }
}
