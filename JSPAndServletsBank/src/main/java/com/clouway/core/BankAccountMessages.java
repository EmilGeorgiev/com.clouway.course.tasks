package com.clouway.core;

/**
 * Created by clouway on 5/26/14.
 */
public interface BankAccountMessages {

  String withdraw();

  String transactionAmount();

  String transactionType();

  String deposit();

  String currentAmount();

  String viewAmount();

  String transactionHistory();

  String sid();
}
