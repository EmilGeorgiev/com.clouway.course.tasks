package com.clouway.core;

import java.util.Date;

/**
 * Represent one transaction
 */
public class Transaction {

  private String transactionType;
  private float amount;
  private Date now;
  private String userId;

  public String getTransactionType() {
    return transactionType;
  }

  public float getAmount() {
    return amount;
  }

  public void setAmount(float amount) {
    this.amount = amount;
  }


  public void setTransactionType(String transactionType) {
    this.transactionType = transactionType;
  }

  public void setNow(Date now) {
    this.now = now;
  }

  public Date getNow() {
    return now;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Transaction that = (Transaction) o;

    if (Float.compare(that.amount, amount) != 0) return false;
    if (now != null ? !now.equals(that.now) : that.now != null) return false;
    if (transactionType != null ? !transactionType.equals(that.transactionType) : that.transactionType != null)
      return false;
    if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = transactionType != null ? transactionType.hashCode() : 0;
    result = 31 * result + (amount != +0.0f ? Float.floatToIntBits(amount) : 0);
    result = 31 * result + (now != null ? now.hashCode() : 0);
    result = 31 * result + (userId != null ? userId.hashCode() : 0);
    return result;
  }
}
