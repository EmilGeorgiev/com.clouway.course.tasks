package com.clouway.core;

import java.util.List;

/**
 * @author <Emil Georiev> <emogeorgiev88@gmail.com>
 */
public interface TransactionRepository {

  /**
   * Retrieve all transaction from database on user.
   *
   * @return List of transaction.
   */
  List<TransactionEntity> getUserTransactions();

}
