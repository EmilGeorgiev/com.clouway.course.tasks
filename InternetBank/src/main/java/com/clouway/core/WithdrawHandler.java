package com.clouway.core;

/**
 * Created by clouway on 7/30/14.
 */
public class WithdrawHandler implements TransactionHandler {

  private final BankRepository bankRepository;

  public WithdrawHandler(BankRepository bankRepository) {
    this.bankRepository = bankRepository;
  }

  @Override
  public String handle(Transaction transaction) {

    transaction.setAmount(-transaction.getAmount());

    return bankRepository.updateBalance(transaction);
  }
}
