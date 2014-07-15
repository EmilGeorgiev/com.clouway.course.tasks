package com.clouway.http;

import com.clouway.core.BankRepository;
import com.clouway.core.Clock;
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

  private Transaction transaction = new Transaction();

  private final BankRepository bankRepository;
  private final Clock clock;

  @Inject
  public TransactionController(BankRepository bankRepository, Clock clock) {
    this.bankRepository = bankRepository;
    this.clock = clock;
  }

  @Post
  public void doPost(){
    transaction.setNow(clock.now());
    System.out.println(transaction.getTransactionType());

    bankRepository.makeTransaction(transaction);
  }

  @Get
  public void doGet() {

  }

  public Transaction getTransaction() {
    return transaction;
  }

  public void setTransaction(Transaction transaction) {
    this.transaction = transaction;
  }
}
