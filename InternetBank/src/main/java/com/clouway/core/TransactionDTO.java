package com.clouway.core;

/**
 * Created by clouway on 7/15/14.
 */
public class TransactionDTO {

  private String transactionType = null;
  private Double amount = null;

  public TransactionDTO() {

  }

  public TransactionDTO(String transactionType, double amount) {
    this.amount = amount;
    this.transactionType = transactionType;
  }

  public String getType() {
    return transactionType;
  }

  public void setTransactionType(String transactionType) {
    this.transactionType = transactionType;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }
}
