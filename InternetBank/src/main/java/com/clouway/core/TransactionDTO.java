package com.clouway.core;

/**
 * Created by clouway on 7/15/14.
 */
public class TransactionDTO {

  private String transactionType;
  private float amount;

  public String getTransactionType() {
    return transactionType;
  }

  public void setTransactionType(String transactionType) {
    this.transactionType = transactionType;
  }

  public float getAmount() {
    return amount;
  }

  public void setAmount(float amount) {
    this.amount = amount;
  }
}
