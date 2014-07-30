package com.clouway.core;

/**
 * Created by emil on 7/30/14.
 */
public interface TransactionFactory {

    TransactionEntity create(Transaction transaction);
}
