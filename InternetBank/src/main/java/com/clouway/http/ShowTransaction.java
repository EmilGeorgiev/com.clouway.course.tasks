package com.clouway.http;

import com.clouway.core.TransactionEntity;
import com.google.sitebricks.rendering.EmbedAs;

import java.util.List;

/**
 * Created by clouway on 7/16/14.
 */
@EmbedAs("Transaction")
public class ShowTransaction {

  private List<TransactionEntity> transactions;

  public List<TransactionEntity> getTransactions() {
    return transactions;
  }

  public void setTransactions(List<TransactionEntity> transactions) {
    this.transactions = transactions;
  }
}
