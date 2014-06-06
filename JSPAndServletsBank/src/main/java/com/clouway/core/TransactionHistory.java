package com.clouway.core;

import com.clouway.core.Transaction;

import java.util.List;

/**
 * Created by clouway on 6/2/14.
 */
public interface TransactionHistory {

  List<Transaction> getUserHistory(int userID);

  void addTransaction(Transaction transaction);

  List<Transaction> getAllTransactions();
}
