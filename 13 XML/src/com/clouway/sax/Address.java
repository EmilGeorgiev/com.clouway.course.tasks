package com.clouway.sax;

/**
 * Created by clouway on 3/31/14.
 */
@XmlComplexElement(packageName ="com.clouway.sax.", beginningMethodName = "set")
public class Address {



  private String street;
  private int number;

  public Address(String street, int number) {
    this.street = street;
    this.number = number;
  }

  public Address() {
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(String number) {

    this.number = Integer.parseInt(number);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Address address = (Address) o;

    if (number != address.number) return false;
    if (street != null ? !street.equals(address.street) : address.street != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = street != null ? street.hashCode() : 0;
    result = 31 * result + number;
    return result;
  }
}
