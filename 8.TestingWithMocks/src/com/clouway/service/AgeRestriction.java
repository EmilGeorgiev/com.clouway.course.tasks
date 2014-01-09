package com.clouway.service;

/**
 * Created by clouway on 1/9/14.
 */
public class AgeRestriction {
  private final int minAge;
  private final int lawfulAge;

  public AgeRestriction(int minAge, int lawfulAge) {

    this.minAge = minAge;
    this.lawfulAge = lawfulAge;
  }

  public int getMinAge() {
    return minAge;
  }

  public int getLawfulAge() {
    return lawfulAge;
  }
}
