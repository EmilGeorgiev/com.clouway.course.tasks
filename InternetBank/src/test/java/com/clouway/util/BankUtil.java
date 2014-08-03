package com.clouway.util;

import com.clouway.core.Clock;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

/**
 * Created by clouway on 7/16/14.
 */
public class BankUtil {
  private final DB connection;
  private final String userName;
  private final Clock clock;

  public BankUtil(DB connection, String userName, Clock clock) {

    this.connection = connection;
    this.userName = userName;
    this.clock = clock;
  }

  public void deposit(Double amount) {

    DBObject query = new BasicDBObject("name", userName);

    DBObject update = new BasicDBObject("$inc", new BasicDBObject("amount", amount));

    users().update(query, update);


    addNewTransaction("deposit", amount);
  }

  public void register(String userName) {

    BasicDBObject user = new BasicDBObject("name", userName)
            .append("amount", 0.0);

    connection.getCollection("users").save(user);
  }

  public void registerUser(String name, String pass) {

    BasicDBObject user = new BasicDBObject("name", name)
            .append("password", pass);

    connection.getCollection("users").save(user);
  }

  private void addNewTransaction(String type, Double amount) {

    BasicDBObject newTransaction = new BasicDBObject("transaction_type", type)
            .append("amount", amount)
            .append("date", clock.now())
            .append("user_name", userName);

    DBCollection collection = connection.getCollection("transactions");

    collection.save(newTransaction);

  }

  private DBCollection users() {
    return connection.getCollection("users");
  }


  public void withdraw(double amount) {
    DBObject query = new BasicDBObject("name", userName);

    DBObject update = new BasicDBObject("$inc", new BasicDBObject("amount", -amount));

    users().update(query, update);

    addNewTransaction("deposit", amount);
  }
}
