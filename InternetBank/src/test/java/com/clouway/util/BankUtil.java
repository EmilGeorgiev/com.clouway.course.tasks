package com.clouway.util;

import com.clouway.core.Transaction;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;

/**
 * Created by clouway on 7/16/14.
 */
public class BankUtil {
  private final DB connection;

  public BankUtil(DB connection) {

    this.connection = connection;
  }

  public void deposit(Transaction transaction, float currentAmount) {

    BasicDBObject userDocument = new BasicDBObject()
            .append("_id", transaction.getUserId())
            .append("account", currentAmount);

    connection.getCollection("user_test").insert(userDocument);

//    BasicDBObject newDocument = new BasicDBObject()
//            .append("$inc", new BasicDBObject("account", transaction.getAmount()));
//
//    connection.getCollection("user_test").update(userDocument, newDocument);
  }
}
