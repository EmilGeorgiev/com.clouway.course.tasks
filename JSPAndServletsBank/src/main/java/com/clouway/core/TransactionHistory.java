package com.clouway.core;

import java.util.List;

/**
 * Created by clouway on 6/2/14.
 */
public interface TransactionHistory {

  List<Transaction> getUserHistory(int userID);

  List<Transaction> getAllTransactions();
}
