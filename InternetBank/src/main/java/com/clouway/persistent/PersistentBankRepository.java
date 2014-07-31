package com.clouway.persistent;

import com.clouway.core.*;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Singleton
public class PersistentBankRepository implements BankRepository, TransactionRepository {

  private final Provider<DB> db;
  private final TransactionMessages transactionMessages;

  @Inject
  public PersistentBankRepository(Provider<DB> db, TransactionMessages transactionMessages) {

    this.db = db;

    this.transactionMessages = transactionMessages;
  }

  /**
   * {@see com.clouway.core.BankRepository}
   */
  @Override
  public Result updateBalance(Transaction transaction) {

    DBObject query = new BasicDBObject("name", transaction.getUserName());

    DBObject projection = new BasicDBObject("$inc",
              new BasicDBObject("amount", transaction.getAmount()));

    users().update(query, projection);

    addNew(transaction);

    return new Result(transactionMessages.success(), getAccount(transaction.getUserName()));
  }

  /**
   * {@see com.clouway.core.TransactionRepository}
   */
  @Override
  public List<Transaction> getAllTransactions(String userName) {
    List<Transaction> transactionList = new ArrayList<Transaction>();

    DBObject query = new BasicDBObject("user_name", userName);

    DBCursor transactions = transactions().find(query);

    while (transactions.hasNext()) {

      BasicDBObject transaction = (BasicDBObject) transactions.next();

      String transactionType = transaction.getString("transaction_type");
      Double amount = transaction.getDouble("amount");
      Date date = transaction.getDate("date");
      String name = transaction.getString("user_name");

      transactionList.add(new Transaction(transactionType, amount, date, name));
    }

    return transactionList;
  }

//  public float getCurrentAmount(String userName) {
//
//    DB db = this.db.get();
//
//    DBObject documentQuery = new BasicDBObject("name", userName);
//
//    DBObject dbObject = users().findOne(documentQuery);
//
//    String account = String.valueOf(dbObject.get("amount"));
//
//    return Float.valueOf(account);
//  }

  /**
   * Get amount on user.
   * @param userName user on who retrieve amount.
   */
  private double getAccount(String userName) {

    DBObject query = new BasicDBObject("name", userName);

    BasicDBObject user = (BasicDBObject) users().findOne(query);

    return user.getDouble("amount");
  }

  /**
   * Insert new transaction in database.
   * @param transaction ne transaction.
   */
  private void addNew(Transaction transaction) {

    DBObject newTransaction = new BasicDBObject("transaction_type",
              transaction.getType())
              .append("amount", transaction.getAmount())
              .append("date", transaction.getDate())
              .append("user_name", transaction.getUserName());

    transactions().insert(newTransaction);
  }

  private DBCollection users() {
    return db.get().getCollection("users");
  }

  private DBCollection transactions() {
    return db.get().getCollection("transactions");
  }
}