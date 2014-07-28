package com.clouway.persistent;

import com.clouway.core.BankMessage;
import com.clouway.core.BankRepository;
import com.clouway.core.DBMessages;
import com.clouway.core.Transaction;
import com.clouway.core.TransactionRepository;
import com.clouway.core.UserMessage;
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

  private final Provider<DB> connection;
  private final UserMessage userMessage;

  @Inject
  public PersistentBankRepository(Provider<DB> connection,
                                  UserMessage userMessage) {

    this.connection = connection;


    this.userMessage = userMessage;
  }

  @Override
  public String makeTransaction(Transaction transaction) {

    if(bankMessage.deposit().equals(transaction.getTransactionType())) {

      deposit(transaction);

    } else {

      transaction.setAmount(-transaction.getAmount());
      withdrawing(transaction);
    }

    return userMessage.success();
  }

  @Override
  public List<Transaction> getAllTransactionByUserName(String userName) {
    List<Transaction> transactionList = new ArrayList<Transaction>();

    BasicDBObject query = new BasicDBObject("user_name", userName);

    DBCursor transactions = transactions().find(query);

    while (transactions.hasNext()) {
      DBObject transaction = transactions.next();

      String transactionType = (String) transaction.get("transaction_type");
      Double amount = (Double) transaction.get("amount");
      Date date = (Date) transaction.get("date");
      String name = (String) transaction.get("name");

      transactionList.add(new Transaction(transactionType, amount, date, name));
    }

    return transactionList;
  }

  public float getCurrentAmount(String userName) {

    DB db = connection.get();

    DBObject documentQuery = new BasicDBObject("name", userName);

    DBObject dbObject = users().findOne(documentQuery);

    String account = String.valueOf(dbObject.get("account"));

    db.collectionExists("users");

    return Float.valueOf(account);
  }

  private void deposit(Transaction transaction) {

    updateDatabases(transaction);
  }

  private void withdrawing(Transaction transaction) {

    updateDatabases(transaction);
  }

  private void updateDatabases(Transaction transaction) {

    DB connection = this.connection.get();

    BasicDBObject updateQuery = new BasicDBObject("name", transaction.getUserName());

    BasicDBObject updateCommand = new BasicDBObject("$inc",
                                             new BasicDBObject("account", transaction.getAmount()));

    users().update(updateQuery, updateCommand);

    addNewTransaction(transaction);

    connection.collectionExists("users");
  }

  private void addNewTransaction(Transaction transaction) {
    BasicDBObject newTransaction = new BasicDBObject("transaction_type", transaction.getTransactionType())
                                .append("amount", transaction.getAmount())
                                .append("date", transaction.getDate())
                                .append("user_name", transaction.getUserName());

    transactions().insert(newTransaction);
  }

  private DBCollection users() {
    return connection.get().getCollection("users");
  }

  private DBCollection transactions() {
    return connection.get().getCollection("transactions");
  }
}
