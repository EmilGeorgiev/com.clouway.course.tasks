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

  @Override
  public void validatesTheYearsByAdding(String age) {
    if (!isValidAge(age, minAge)) {
      throw new IllegalArgumentException("Years should be " + minAge + " to " + maxAge + " !");
    }
  }

  @Override
  public void validatesTheYearsByGetting(String age) {
    if (!isValidAge(age, lawfulAge)) {
      throw new IllegalArgumentException("Years may be from " + lawfulAge + " to " + maxAge + ".");
    }
  }

  private boolean isValidAge(String age, int minimumPossibleValue) {
    int currentAge = Integer.parseInt(age);
    if (currentAge >= minimumPossibleValue && currentAge <= maxAge) {
      return true;
    }
    return false;
  }
}
