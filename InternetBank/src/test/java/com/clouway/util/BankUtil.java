package com.clouway.util;

import com.clouway.core.Transaction;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

/**
 * Created by clouway on 7/16/14.
 */
public class BankUtil {
  private final DB connection;

  public BankUtil(DB connection) {

    this.connection = connection;
  }

  public void makeTransaction(Transaction transaction) {

    BasicDBObject updateQuery = new BasicDBObject("_id", transaction.getUserName());

    BasicDBObject updateDocument = new BasicDBObject("$inc", new BasicDBObject("account", transaction.getAmount()));

    connection.getCollection("users").update(updateQuery, updateDocument);

    addNewTransaction(transaction);
  }

  public void registerUser(String userId) {

    BasicDBObject user = new BasicDBObject("_id", userId)
            .append("account", 0.0);

    connection.getCollection("users").save(user);
  }

  public void registerUser(String name, String pass) {
    BasicDBObject user = new BasicDBObject("name", name)
            .append("password", pass);

    connection.getCollection("users").save(user);
  }

  private void addNewTransaction(Transaction transaction) {
    BasicDBObject newTransaction = new BasicDBObject("transactionType", transaction.getTransactionType())
            .append("amount", transaction.getAmount())
            .append("date", transaction.getDate())
            .append("user_id", transaction.getUserName());

    DBCollection collection = connection.getCollection("transactions");

    collection.save(newTransaction);

  }


}
