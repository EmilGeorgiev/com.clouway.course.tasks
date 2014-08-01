package com.clouway.core;

import java.util.Date;

/**
 * Represent one transaction
 */
public class Transaction {

  private double amount;
  private Date date;

  public Transaction(double amount, Date date) {

    this.amount = amount;
    this.date = date;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
}
