package com.clouway.core;

import java.util.Date;

/**
 * Represent one transaction
 */
public class Transaction {

  private enum Type {DEPOSIT, WITHDRAW}
  private String type;
  private double amount;
  private Date date;
  private String userName;

  public Transaction(String type, double amount, Date date, String userName) {

    this.type = type;
    this.amount = amount;
    this.date = date;
    this.userName = userName;
  }

  public String getType() {
    return type;
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

  public void setType(String transactionType) {
    this.type = transactionType;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Transaction that = (Transaction) o;

    if (Double.compare(that.amount, amount) != 0) return false;
    if (date != null ? !date.equals(that.date) : that.date != null) return false;
    if (type != null ? !type.equals(that.type) : that.type != null)
      return false;
    if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    result = type != null ? type.hashCode() : 0;
    temp = Double.doubleToLongBits(amount);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    result = 31 * result + (date != null ? date.hashCode() : 0);
    result = 31 * result + (userName != null ? userName.hashCode() : 0);
    return result;
  }
}
