package com.clouway.persistent;

import com.clouway.core.BankMessage;
import com.clouway.core.BankRepository;
import com.clouway.core.DBMessages;
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
  public String makeTransaction(Transaction transaction) {

    if(bankMessage.deposit().equals(transaction.getTransactionType())) {

      deposit(transaction);

    } else {

      transaction.setAmount(-transaction.getAmount());
      withdrawing(transaction);
    }

    return DBMessages.TRANSACTION_SUCCESS;
  }

  @Override
  public List<Transaction> getAllTransactionByUserName(String userName) {
    List<Transaction> transactionList = new ArrayList<Transaction>();

    BasicDBObject wereClause = new BasicDBObject(USER_NAME, userName);

    DBCursor transactions = transactions().find(wereClause);

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

  public float getCurrentAmount(String userName) {

    DB db = connection.get();

    DBObject documentQuery = new BasicDBObject(NAME, userName);

    DBObject dbObject = users().findOne(documentQuery);

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

    addNewTransaction(transaction);

    connection.collectionExists(USERS);
  }

  private void addNewTransaction(Transaction transaction) {
    BasicDBObject newTransaction = new BasicDBObject(TRANSACTION_TYPE, transaction.getTransactionType())
            .append(AMOUNT, transaction.getAmount())
            .append(DATE, transaction.getDate())
            .append(USER_NAME, transaction.getUserName());

    transactions().insert(newTransaction);
  }

  private DBCollection users() {
    return connection.get().getCollection(USERS);
  }

  private DBCollection transactions() {
    return connection.get().getCollection(TRANSACTIONS);
  }
}
