package com.clouway.operationtable;

/**
 * Created by clouway on 3/13/14.
 */
public class Car {

  private final String model;
  private final String country;
  private final String yearManufacture;
  private int registrationNumber;


  public Car(int registrationNumber, String model, String Country, String yearManufacture) {
    this.model = model;
    country = Country;
    this.yearManufacture = yearManufacture;
    this.registrationNumber = registrationNumber;
  }

  public String getModel() {
    return model;
  }

  public String getCountry() {
    return country;
  }

  public int getRegistrationNumber() {
    return registrationNumber;
  }

  public String getYearManufacture() {
    return yearManufacture;
  }
}
