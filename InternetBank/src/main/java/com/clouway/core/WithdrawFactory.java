package com.clouway.core;

public class WithdrawFactory implements TransactionFactory {

    @Override
    public TransactionEntity create(Transaction transaction) {

        transaction.setAmount(-transaction.getAmount());

        return new TransactionEntity(transaction.getType(), transaction.getUserName(),
                                    -transaction.getAmount(), transaction.getDate());
    }
}
