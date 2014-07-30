package com.clouway.http;

import com.clouway.core.BankTransaction;
import com.clouway.core.Transaction;
import com.clouway.core.TransactionMessages;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

public class Deposit implements BankTransaction {

  private final DB db;
  private final TransactionMessages transactionMessages;

  public Deposit(DB db, TransactionMessages transactionMessages) {

    this.db = db;
    this.transactionMessages = transactionMessages;
  }

  @Override
  public String execute(Transaction transaction) {

    BasicDBObject query = new BasicDBObject("name", transaction.getUserName());

    BasicDBObject update = new BasicDBObject("$inc",
            new BasicDBObject("account", transaction.getAmount()));

    users().update(query, update);

    addNew(transaction);

    db.collectionExists("users");

    return transactionMessages.success();
  }

  private void addNew(Transaction transaction) {
    BasicDBObject newTransaction = new BasicDBObject("transaction_type", transaction.getType())
            .append("amount", transaction.getAmount())
            .append("date", transaction.getDate())
            .append("user_name", transaction.getUserName());

    transactions().insert(newTransaction);
  }

  private DBCollection users() {
    return db.getCollection("users");
  }

  private DBCollection transactions() {
    return db.getCollection("transactions");
  }
}
