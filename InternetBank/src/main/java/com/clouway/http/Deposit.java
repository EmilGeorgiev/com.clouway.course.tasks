package com.clouway.http;

import com.clouway.core.BankTransaction;
import com.clouway.core.Transaction;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

/**
 * Created by clouway on 7/29/14.
 */
public class Deposit implements BankTransaction {


  private final DB connection;

  public Deposit(DB connection) {

    this.connection = connection;
  }

  @Override
  public void execute(Transaction transaction) {

    BasicDBObject updateQuery = new BasicDBObject("name", transaction.getUserName());

    BasicDBObject updateCommand = new BasicDBObject("$inc",
            new BasicDBObject("account", transaction.getAmount()));

    users().update(updateQuery, updateCommand);

    connection.collectionExists("users");
  }

  private DBCollection users() {
    return connection.getCollection("users");
  }
}
