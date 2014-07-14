package com.clouway.http;

import com.clouway.core.BankRepository;
import com.clouway.core.Transaction;
import com.google.inject.Inject;
import com.google.sitebricks.At;
import com.google.sitebricks.http.Get;
import com.google.sitebricks.http.Post;

/**
 * Created by clouway on 7/14/14.
 */
@At("/transactionController")
public class TransactionController {

  private Transaction transaction;
  private final BankRepository bankRepository;

  @Inject
  public TransactionController(BankRepository bankRepository) {
    this.bankRepository = bankRepository;
  }

  @Post
  public void doPost() {
    bankRepository.makeTransaction(transaction);
  }

  @Get
  public void doGet() {

  }
}
