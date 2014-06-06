package com.clouway.core;

/**
 * Created by clouway on 5/29/14.
 */
public class Transaction {

  private final String transfer;
  private final String dateTransaction;
  private final int amountTransaction;
  private final int userID;

  public Transaction(String transfer, int amountTransaction, String dateTransaction, int userID) {
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

  public String getDateTransaction() {
    return dateTransaction;
  }

  public int getAmountTransaction() {
    return amountTransaction;
  }


  @Override
  public String toString() {
    return  dateTransaction + " | " + amountTransaction +  " | " + transfer;
  }
}
