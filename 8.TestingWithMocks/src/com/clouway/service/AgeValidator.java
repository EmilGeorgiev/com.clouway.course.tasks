package com.clouway.service;

/**
 * Created by clouway on 1/7/14.
 */
public class AgeValidator implements Validator {
  private final int maxAge;

  public AgeValidator(int maxAge) {
    this.maxAge = maxAge;
  }

  /**
   * Verify that the years can be added to the database if can't throw exception.
   * @param age year to check.
   * @param ageLimit
   */
  @Override
  public void validateYears(int age, int ageLimit) {
    if (!isValidAge(age, ageLimit)) {
      throw new IllegalArgumentException("Years should be " + ageLimit + " to " + maxAge + " !");
    }
  }

  /**
   * Verify that the years have limits, if not throw exception.
   * @param age years to check
   * @param ageLimit minimum allowable value of the years.
   * @return true if years is valid.
   */
  private boolean isValidAge(int age, int ageLimit) {
    if (age >= ageLimit && age <= maxAge) {
      return true;
    }
    return false;
  }
}
