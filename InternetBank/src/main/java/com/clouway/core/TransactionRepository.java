package com.clouway.core;

import java.util.List;

/**
 * Created by clouway on 7/16/14.
 */
public interface TransactionRepository {
  List<Transaction> getAllTransactionByUserID(Object userId);

}
