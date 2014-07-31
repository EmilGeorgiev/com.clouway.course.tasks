package com.clouway.http;

/**
 * Represent data transfer object who we use for retrieve and sent data to user.
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
