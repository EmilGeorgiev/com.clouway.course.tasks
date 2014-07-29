package com.clouway.http;

import com.clouway.core.BankTransaction;
import com.clouway.core.Transaction;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

/**
 * Created by clouway on 7/29/14.
 */
public class Withdraw implements BankTransaction {

  private final DB connection;

  public Withdraw(DB connection) {
    this.connection = connection;
  }

  @Override
  public void execute(Transaction transaction) {
    BasicDBObject updateQuery = new BasicDBObject("name", transaction.getUserName());

    double amount = transaction.getAmount();

    BasicDBObject updateCommand = new BasicDBObject("$inc",
            new BasicDBObject("account", -amount));

    users().update(updateQuery, updateCommand);

    connection.collectionExists("users");
  }

  private DBCollection users() {
    return connection.getCollection("users");
  }
}
