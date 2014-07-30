package com.clouway.core;

/**
 * Created by clouway on 7/14/14.
 */
public interface BankRepository {

  String updateBalance(TransactionEntity transaction);

  double getAccountBy(String userName);

}
