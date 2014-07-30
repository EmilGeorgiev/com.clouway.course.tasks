package com.clouway.core;

public class DepositFactory implements TransactionFactory {

    @Override
    public TransactionEntity create(Transaction transaction) {
        return new TransactionEntity(transaction.getType(), transaction.getUserName(),
                -transaction.getAmount(), transaction.getDate());
    }
}
