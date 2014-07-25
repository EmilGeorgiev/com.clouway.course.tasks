package com.clouway.persistent;

import com.clouway.core.BankMessage;
import com.clouway.core.BankRepository;
import com.clouway.core.Transaction;
import com.clouway.core.TransactionRepository;
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

import static com.clouway.core.DBMessages.*;

/**
 * Created by clouway on 7/15/14.
 */
@Singleton
public class PersistentBankRepository implements BankRepository, TransactionRepository {

  private final Provider<DB> connection;
  private final BankMessage bankMessage;

  @Inject
  public PersistentBankRepository(Provider<DB> connection,
                                  BankMessage bankMessage) {

    this.connection = connection;
    this.bankMessage = bankMessage;

  }

  @Override
  public void makeTransaction(Transaction transaction) {

    if(bankMessage.deposit().equals(transaction.getTransactionType())) {

      deposit(transaction);
    } else {

      transaction.setAmount(-transaction.getAmount());
      withdrawing(transaction);
    }
  }

  public float getCurrentAmount(String userId) {

    DB db = connection.get();

    DBObject dbObject = users().findOne(userId);

    String account = String.valueOf(dbObject.get(ACCOUNT));

    db.collectionExists(USERS);

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

    BasicDBObject updateQuery = new BasicDBObject(NAME, transaction.getUserName());

    BasicDBObject updateCommand = new BasicDBObject("$inc",
                                             new BasicDBObject(ACCOUNT, transaction.getAmount()));

    users().update(updateQuery, updateCommand);

    addNewTransaction(transaction, connection);

    connection.collectionExists(USERS);
  }

  private void addNewTransaction(Transaction transaction, DB connection) {
    BasicDBObject newTransaction = new BasicDBObject(TRANSACTION_TYPE, transaction.getTransactionType())
            .append(AMOUNT, transaction.getAmount())
            .append(DATE, transaction.getDate())
            .append(NAME, transaction.getUserName());

    connection.getCollection(TRANSACTIONS).insert(newTransaction);
  }

  private DBCollection getCollection(String collectionName) {
    return connection.get().getCollection(collectionName);
  }

  @Override
  public List<Transaction> getAllTransactionByUserName(String userName) {
    List<Transaction> transactionList = new ArrayList<Transaction>();

    BasicDBObject wereClause = new BasicDBObject(NAME, userName);

    DBCursor transactions = connection.get().getCollection(TRANSACTIONS).find(wereClause);

    while (transactions.hasNext()) {
      DBObject transaction = transactions.next();

      String transactionType = (String) transaction.get(TRANSACTION_TYPE);
      Double amount = (Double) transaction.get(AMOUNT);
      Date date = (Date) transaction.get(DATE);
      String name = (String) transaction.get(NAME);

      transactionList.add(new Transaction(transactionType, amount, date, name));
    }

    return transactionList;
  }

  private DBCollection users() {
    return connection.get().getCollection(USERS);
  }

  private DBCollection sessions() {
    return connection.get().getCollection(SESSIONS);
  }

}
