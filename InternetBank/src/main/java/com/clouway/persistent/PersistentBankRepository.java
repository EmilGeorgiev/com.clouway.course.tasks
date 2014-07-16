package com.clouway.persistent;

import com.clouway.core.BankMessage;
import com.clouway.core.BankRepository;
import com.clouway.core.DBMessage;
import com.clouway.core.Transaction;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

/**
 * Created by clouway on 7/15/14.
 */
@Singleton
public class PersistentBankRepository implements BankRepository {

  private final Provider<DB> dbProvider;
  private final BankMessage bankMessage;
  private final DBMessage messagesDB;

  @Inject
  public PersistentBankRepository(Provider<DB> dbProvider,
                                  BankMessage bankMessage,
                                  DBMessage messagesDB) {

    this.dbProvider = dbProvider;

    this.bankMessage = bankMessage;
    this.messagesDB = messagesDB;
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

  public float getCurrentAmount(Object userId) {

    DB db = dbProvider.get();

    DBCollection userColl = db.getCollection(messagesDB.collectionUser());

    DBObject dbObject = userColl.findOne(userId);
    String amount = String.valueOf(dbObject.get(messagesDB.fieldAccount()));

    return Float.valueOf(amount);
  }

  private void deposit(Transaction transaction) {

    updateDatabases(transaction);
  }

  private void withdrawing(Transaction transaction) {

    updateDatabases(transaction);
  }

  private void updateDatabases(Transaction transaction) {

    DB connection = dbProvider.get();

    DBCollection userCollection = connection.getCollection(messagesDB.collectionUser());

    BasicDBObject updateQuery = new BasicDBObject();

    updateQuery.put(messagesDB.fieldId(), transaction.getUserId());

    BasicDBObject updateCommand = new BasicDBObject();

    updateCommand.put(messagesDB.operatorInc(), new BasicDBObject(messagesDB.fieldAccount(), transaction.getAmount()));

    userCollection.update(updateQuery, updateCommand);

    //Add new transaction in database.
//    BasicDBObject newTransaction = new BasicDBObject(messagesDB.fieldTransactionType(), transaction.getTransactionType())
//            .append(messagesDB.fieldAmount(), transaction.getAmount())
//            .append(messagesDB.fieldDate(), transaction.getDate())
//            .append(messagesDB.fieldUserId(), transaction.getUserId());
//
//    connection.getCollection(messagesDB.collectionTransaction()).insert(newTransaction);
  }
}
