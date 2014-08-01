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

  public final Provider<DB> db;
  public final TransactionMessages transactionMessages;
  public final Provider<CurrentUser> currentUser;
  public final Clock clock;

  @Inject
  public PersistentBankRepository(Provider<DB> db,
                                  TransactionMessages transactionMessages,
                                  Provider<CurrentUser> currentUser,
                                  Clock clock) {

    this.db = db;
    this.transactionMessages = transactionMessages;
    this.currentUser = currentUser;
    this.clock = clock;
  }

  /**
   * {@see com.clouway.core.BankRepository}
   */
  @Override
  public TransactionInfo deposit(Double amount) {

    DBObject query = new BasicDBObject("name", currentUser.get().getName());

    DBObject fields = new BasicDBObject("amount", 1);

    DBObject update = new BasicDBObject("$inc", new BasicDBObject("amount", amount));

    DBObject object = users().findAndModify(query, fields, null, false, update, true, true);

    Double currentAmount = (Double) object.get("amount");

    addNew("deposit", currentAmount);

    return new TransactionInfo(transactionMessages.success(), currentAmount);
  }

  /**
   * {@see com.clouway.core.BankRepository}
   */
  @Override
  public TransactionInfo withdraw(Double amount) {

    DBObject query = new BasicDBObject("name", currentUser.get().getName());

    DBObject fields = new BasicDBObject("amount", 1);

    DBObject update = new BasicDBObject("$inc", new BasicDBObject("amount", -amount));

    DBObject object = users().findAndModify(query, fields, null, false, update, true, true);

    Double currentAmount = (Double) object.get("amount");

    addNew("deposit", currentAmount);

    return new TransactionInfo(transactionMessages.success(), currentAmount);
  }

  /**
   * {@see com.clouway.core.TransactionRepository}
   */
  @Override
  public List<TransactionEntity> getUserTransactions() {
    List<TransactionEntity> transactions = new ArrayList<TransactionEntity>();

    DBObject query = new BasicDBObject("user_name", currentUser.get().getName());

    DBCursor transactionsCursor = transactions().find(query);

    while (transactionsCursor.hasNext()) {
      BasicDBObject transaction = (BasicDBObject) transactionsCursor.next();

      String transactionType = transaction.getString("transaction_type");

      Double amount = transaction.getDouble("amount");
      Date date = transaction.getDate("date");

      transactions.add(new TransactionEntity(transactionType, amount, date));
    }

    return transactions;
  }

  /**
   * Insert new transaction in database.
   */
  private void addNew(String type, Double currentAmount) {

    DBObject transactionEntity = new BasicDBObject("transaction_type", type)
              .append("amount", currentAmount)
              .append("date", clock.now())
              .append("user_name", currentUser.get().getName());

    transactions().insert(transactionEntity);
  }

  private DBCollection users() {
    return db.get().getCollection("users");
  }

  private DBCollection transactions() {
    return db.get().getCollection("transactions");
  }
}