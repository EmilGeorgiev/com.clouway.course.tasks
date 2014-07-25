package com.clouway.core;

import java.util.Date;

/**
 * Represent one transaction
 */
public class Transaction {

  private final String transactionType;
  private double amount;
  private final Date date;
  private final String userName;

  public Transaction(String transactionType, double amount, Date date, String userName) {

    this.transactionType = transactionType;
    this.amount = amount;

    this.date = date;
    this.userName = userName;
  }

  public String getTransactionType() {
    return transactionType;
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

  public String getUserName() {
    return userName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Transaction that = (Transaction) o;

    if (Double.compare(that.amount, amount) != 0) return false;
    if (date != null ? !date.equals(that.date) : that.date != null) return false;
    if (transactionType != null ? !transactionType.equals(that.transactionType) : that.transactionType != null)
      return false;
    if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    result = transactionType != null ? transactionType.hashCode() : 0;
    temp = Double.doubleToLongBits(amount);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    result = 31 * result + (date != null ? date.hashCode() : 0);
    result = 31 * result + (userName != null ? userName.hashCode() : 0);
    return result;
  }
}
