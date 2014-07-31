package com.clouway.core;

/**
 * Create <code>TransactionEntity</code> object who will save in database.
 */
public class DepositCreator implements TransactionEntityFactory {

  @Override
  public TransactionEntity create(Transaction transaction) {
    return new TransactionEntity(transaction.getType(), transaction.getUserName(),
            transaction.getAmount(), transaction.getDate());
  }
}
