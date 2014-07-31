package com.clouway.core;

/**
 * Created by clouway on 7/30/14.
 */
public class DepositHandler implements TransactionHandler {

  private final BankRepository bankRepository;

  public DepositHandler(BankRepository bankRepository) {
    this.bankRepository = bankRepository;

  }

  @Override
  public String handle(Transaction transaction) {

    return bankRepository.updateBalance(transaction);
  }
}
