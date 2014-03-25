package com.clouway.facebook;

/**
 * Created by clouway on 3/21/14.
 */
public class Address {
  private int id;
  private final String city;
  private final String street;
  private final int postCode;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Address address = (Address) o;

    if (id != address.id) return false;
    if (postCode != address.postCode) return false;
    if (city != null ? !city.equals(address.city) : address.city != null) return false;
    if (street != null ? !street.equals(address.street) : address.street != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (city != null ? city.hashCode() : 0);
    result = 31 * result + (street != null ? street.hashCode() : 0);
    result = 31 * result + postCode;
    return result;
  }

  public int getId() {

    return id;
  }

  public String getCity() {
    return city;
  }

  public String getStreet() {
    return street;
  }

  public int getPostCode() {
    return postCode;
  }

  public Address(int id, String city, String street, int postCode) {

    this.id = id;
    this.city = city;
    this.street = street;
    this.postCode = postCode;
  }
}
