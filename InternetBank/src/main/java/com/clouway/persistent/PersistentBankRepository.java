package com.clouway.persistent;

import com.clouway.core.BankRepository;
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

  @Inject
  public PersistentBankRepository(Provider<DB> dbProvider) {

    this.dbProvider = dbProvider;

  }

  @Override
  public void makeTransaction(Transaction transaction) {

    if("deposit".equals(transaction.getTransactionType())) {

      deposit(transaction);
    } else {

      transaction.setAmount(-transaction.getAmount());
      withdrawing(transaction);
    }
  }

  public float getCurrentAmount(Object userId) {

    DB db = dbProvider.get();

    DBCollection userColl = db.getCollection("user");

    DBObject dbObject = userColl.findOne(userId);

    return (Float) dbObject.get("account");
  }

  private void deposit(Transaction transaction) {

    updateDatabases(transaction);
  }

  private void withdrawing(Transaction transaction) {

    updateDatabases(transaction);
  }

  private void updateDatabases(Transaction transaction) {

    DB connection = dbProvider.get();

    DBCollection userCollection = connection.getCollection("user");

    BasicDBObject updateQuery = new BasicDBObject();

    updateQuery.put("_id", transaction.getUserId());

    BasicDBObject updateCommand = new BasicDBObject();

    updateCommand.put("$inc", new BasicDBObject("account", transaction.getAmount()));

    userCollection.update(updateQuery, updateCommand);

    //Add new transaction in database.
    BasicDBObject newTransaction = new BasicDBObject("transactionType", transaction.getTransactionType())
            .append("amount", transaction.getAmount())
            .append("date", transaction.getDate())
            .append("user_id", transaction.getUserId());

    connection.getCollection("transaction").insert(newTransaction);
  }
}
