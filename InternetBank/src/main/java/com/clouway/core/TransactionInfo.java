package com.clouway.core;

/**
 * Contains result of executed transaction.
 */
public class TransactionInfo {

  private final String message;
  private final Double currentAmount;

  public TransactionInfo(String message, Double currentAmount) {
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
