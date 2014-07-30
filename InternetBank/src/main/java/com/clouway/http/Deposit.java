package com.clouway.http;

import com.clouway.core.BankTransaction;
import com.clouway.core.Transaction;
import com.clouway.core.UserMessage;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

public class Deposit implements BankTransaction {

  private final DB db;
  private final UserMessage userMessage;

  public Deposit(DB db, UserMessage userMessage) {

    this.db = db;
    this.userMessage = userMessage;
  }

  @Override
  public String execute(Transaction transaction) {

    BasicDBObject updateQuery = new BasicDBObject("name", transaction.getUserName());

    BasicDBObject updateCommand = new BasicDBObject("$inc",
            new BasicDBObject("account", transaction.getAmount()));

    users().update(updateQuery, updateCommand);

    addNewTransaction(transaction);

    db.collectionExists("users");

    return userMessage.successTransaction();
  }

  private void addNewTransaction(Transaction transaction) {
    BasicDBObject newTransaction = new BasicDBObject("transaction_type", transaction.getTransactionType())
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
