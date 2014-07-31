package com.clouway.core;

/**
 * Contains result of executed transaction.
 */
public class Result {

  private final String message;
  private final Double currentAmount;

  public Result(String message, Double currentAmount) {
    this.message = message;
    this.currentAmount = currentAmount;
  }

  public String getMessage() {
    return message;
  }

  public Double getCurrentAmount() {
    return currentAmount;
  }
}
