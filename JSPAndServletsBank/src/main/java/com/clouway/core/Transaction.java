package com.clouway.core;

import java.sql.Timestamp;

/**
 * Created by clouway on 5/29/14.
 */
public class Transaction {

  private final String transfer;
  private final Timestamp dateTransaction;
  private final float amountTransaction;
  private final int userID;

  public Transaction(String transfer, float amountTransaction, Timestamp dateTransaction, int userID) {
    this.transfer = transfer;

    this.dateTransaction = dateTransaction;

    this.amountTransaction = amountTransaction;

    this.userID = userID;
  }

  public int getUserID() {
    return userID;
  }

  public String getTransfer() {
    return transfer;
  }

  public Timestamp getDateTransaction() {
    return dateTransaction;
  }

  public float getAmountTransaction() {
    return amountTransaction;
  }


  @Override
  public String toString() {
    return  dateTransaction + " | " + amountTransaction +  " | " + transfer;
  }
}
