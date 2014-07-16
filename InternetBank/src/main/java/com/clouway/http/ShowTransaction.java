package com.clouway.http;

import com.clouway.core.Transaction;
import com.google.sitebricks.rendering.EmbedAs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clouway on 7/16/14.
 */
@EmbedAs("Transaction")
public class ShowTransaction {

  private List<Transaction> transactionList = new ArrayList<Transaction>();

  public List<Transaction> getTransactionList() {
    return transactionList;
  }

  public void setTransactionList(List<Transaction> transactionList) {
    this.transactionList = transactionList;
  }
}
