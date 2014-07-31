package com.clouway.core;

import java.util.List;

/**
 * Liaise with database
 */
public interface TransactionRepository {

  /**
   * Retrieve all transaction from database on user.
   * @param userName Name on the user.
   * @return List of transaction.
   */
  List<TransactionEntity> getAllTransactionsBy(String userName);

}
