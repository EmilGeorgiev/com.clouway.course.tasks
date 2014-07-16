package com.clouway.core;

import java.util.Date;

/**
 * Represent one transaction
 */
public class Transaction {


  private final String transactionType;
  private float amount;
  private final Date date;
  private final Object userId;

  public Transaction(String transactionType, float amount, Date date, Object userId) {

    this.transactionType = transactionType;
    this.amount = amount;
    this.date = date;
    this.userId = userId;
  }

  public String getTransactionType() {
    return transactionType;
  }

  public float getAmount() {
    return amount;
  }

  public void setAmount(float amount) {
    this.amount = amount;
  }

  public Date getDate() {
    return date;
  }

  public Object getUserId() {
    return userId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Transaction that = (Transaction) o;

    if (Float.compare(that.amount, amount) != 0) return false;
    if (date != null ? !date.equals(that.date) : that.date != null) return false;
    if (transactionType != null ? !transactionType.equals(that.transactionType) : that.transactionType != null)
      return false;
    if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = transactionType != null ? transactionType.hashCode() : 0;
    result = 31 * result + (amount != +0.0f ? Float.floatToIntBits(amount) : 0);
    result = 31 * result + (date != null ? date.hashCode() : 0);
    result = 31 * result + (userId != null ? userId.hashCode() : 0);
    return result;
  }
}
