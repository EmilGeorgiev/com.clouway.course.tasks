package com.clouway.service;

/**
 * Created by clouway on 1/7/14.
 */
public class AgeValidator implements Validator {
  private final int maxAge;
  private final int lawfulAge;
  private final int minAge;

  public AgeValidator(int minAge, int maxAge, int lawfulAge) {
    this.minAge = minAge;
    this.maxAge = maxAge;
    this.lawfulAge = lawfulAge;
  }

  /**
   * Verify that the years can be added to the database if can't throw exception.
   * @param age year to check.
   */
  @Override
  public void validatesTheYearsByAdding(int age) {
    if (!isValidAge(age, minAge)) {
      throw new IllegalArgumentException("Years should be " + minAge + " to " + maxAge + " !");
    }
  }

  /**
   * Verify that the years cen be getting from the database, if can't throw exception.
   * @param age that will take years.
   */
  @Override
  public void validatesTheYearsByGetting(int age) {
    if (!isValidAge(age, lawfulAge)) {
      throw new IllegalArgumentException("Years may be from " + lawfulAge + " to " + maxAge + ".");
    }
  }

  /**
   * Verify that the years have limits, if not throw exception.
   * @param age years to check
   * @param minimumPossibleValue minimum allowable value of the years.
   * @return true if years is valid.
   */
  private boolean isValidAge(int age, int minimumPossibleValue) {
    if (age >= minimumPossibleValue && age <= maxAge) {
      return true;
    }
    return false;
  }
}
