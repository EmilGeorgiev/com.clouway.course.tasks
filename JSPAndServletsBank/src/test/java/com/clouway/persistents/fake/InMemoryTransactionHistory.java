package com.clouway.persistents.fake;

import com.clouway.objects.Transaction;
import com.clouway.objects.TransactionHistory;

import java.util.List;

/**
 * Created by clouway on 6/3/14.
 */
public class InMemoryTransactionHistory implements TransactionHistory {
  @Override
  public List<Transaction> getUserHistory(int userID) {
    return null;
  }

  @Override
  public void addTransaction(Transaction transaction) {

  }

  @Override
  public List<Transaction> getAllTransactions() {
    return null;
  }
}
