package com.clouway.core;

/**
 * Created object who communicate with database.
 */
public interface TransactionEntityFactory {

    TransactionEntity create(Transaction transaction);
}
