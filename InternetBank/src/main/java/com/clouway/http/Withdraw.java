package com.clouway.http;

import com.clouway.core.BankTransaction;
import com.clouway.core.Transaction;
import com.clouway.core.TransactionMessages;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

/**
 * Created by clouway on 7/29/14.
 */
public class Withdraw implements BankTransaction {

  private final DB connection;
  private final TransactionMessages transactionMessages;

  public Withdraw(DB connection, TransactionMessages transactionMessages) {
    this.connection = connection;
    this.transactionMessages = transactionMessages;
  }

  @Override
  public String execute(Transaction transaction) {
    BasicDBObject updateQuery = new BasicDBObject("name", transaction.getUserName());

    double amount = transaction.getAmount();
    double currentAmount = getUserAccountBy(transaction.getUserName());

    if (amount > currentAmount) {
      return transactionMessages.failed();
    }

    BasicDBObject updateCommand = new BasicDBObject("$inc",
                         new BasicDBObject("account", -amount));

    users().update(updateQuery, updateCommand);

    connection.collectionExists("users");

    addNewTransaction(transaction);

    return transactionMessages.success();
  }

  private Double getUserAccountBy(String userName) {
    BasicDBObject query = new BasicDBObject("name", userName);

    return (Double) users().findOne(query).get("account");

  }

  private void addNewTransaction(Transaction transaction) {
    BasicDBObject newTransaction = new BasicDBObject("transaction_type", transaction.getTransactionType())
            .append("amount", transaction.getAmount())
            .append("date", transaction.getDate())
            .append("user_name", transaction.getUserName());

    transactions().insert(newTransaction);
  }

  private DBCollection users() {
    return connection.getCollection("users");
  }

  private DBCollection transactions() {
    return connection.getCollection("transactions");
  }
}




