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

  @Override
  public String updateBalance(TransactionEntity transaction) {

    BasicDBObject query = new BasicDBObject("name", transaction.getUserName());

    BasicDBObject update = new BasicDBObject("$inc",
              new BasicDBObject("amount", transaction.getAmount()));

    users().update(query, update);

    addNew(transaction);

    db.get().collectionExists("users");

    return transactionMessages.success() + "&currentAmount="+getAccountBy(transaction.getUserName());
  }

  @Override
  public double getAccountBy(String userName) {
    BasicDBObject query = new BasicDBObject("name", userName);

    return (Double) users().findOne(query).get("amount");
  }

  @Override
  public List<Transaction> getAllTransactionsBy(String userName) {
    List<Transaction> transactionList = new ArrayList<Transaction>();

    BasicDBObject query = new BasicDBObject("user_name", userName);

    DBCursor transactions = transactions().find(query);

    while (transactions.hasNext()) {
      DBObject transaction = transactions.next();

      String transactionType = (String) transaction.get("transaction_type");
      Double amount = (Double) transaction.get("amount");
      Date date = (Date) transaction.get("date");
      String name = (String) transaction.get("user_name");

      transactionList.add(new Transaction(transactionType, amount, date, name));
    }

    return transactionList;
  }

  public float getCurrentAmount(String userName) {

    DB db = this.db.get();

    DBObject documentQuery = new BasicDBObject("name", userName);

    DBObject dbObject = users().findOne(documentQuery);

    String account = String.valueOf(dbObject.get("amount"));

    db.collectionExists("users");

    return Float.valueOf(account);
  }

  private void addNew(TransactionEntity transaction) {

    BasicDBObject newTransaction = new BasicDBObject("transaction_type",
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