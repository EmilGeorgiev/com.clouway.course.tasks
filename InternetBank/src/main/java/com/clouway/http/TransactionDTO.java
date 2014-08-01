package com.clouway.http;

/**
 * Represent data transfer object who we use for retrieve and sent data to user.
 */
public class TransactionDTO {

  private Double amount = null;

  public TransactionDTO() {

  }

  public TransactionDTO(double amount) {
    this.amount = amount;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = Double.valueOf(amount);
  }
}
