package com.clouway.objects;

import java.util.List;

/**
 * Created by clouway on 6/2/14.
 */
public interface TransactionHistory {

  List<Transaction> getUserHistory(int userID);

  void addTransaction(Transaction transaction);

  List<Transaction> getAllTransactions();
}
