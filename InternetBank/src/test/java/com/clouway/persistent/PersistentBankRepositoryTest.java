package com.clouway.persistent;

import com.clouway.core.Clock;
import com.clouway.core.Time;
import com.clouway.core.Transaction;
import com.google.inject.util.Providers;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.junit.Before;
import org.junit.Test;

import java.net.UnknownHostException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by clouway on 7/15/14.
 */
public class PersistentBankRepositoryTest {

  private PersistentBankRepository persistentBankRepository;

  private Transaction transaction;

  private DB connection;

  private Clock clock = new Time();

  @Before
  public void setUp() throws UnknownHostException {

    MongoClient client = new MongoClient();

    connection = client.getDB("Bank");

    persistentBankRepository = new PersistentBankRepository(Providers.of(connection));
  }

  @Test
  public void depositSameValue() throws Exception {
    pretendThatDeposit(amount(30), currentAmount(50), transactionType("deposit"), userId(23));

    persistentBankRepository.makeTransaction(transaction);

    float currentAmount = persistentBankRepository.getCurrentAmount(userId(23));

    assertThat(currentAmount, is(80F));
  }

  private void pretendThatDeposit(float amount, float currentAmount, String transactionType, int userId) {

    transaction = new Transaction(transactionType, amount, clock.now(), userId);


    BasicDBObject updateQuery = new BasicDBObject();
    updateQuery.put("_id", userId);

    BasicDBObject updateCommand = new BasicDBObject();
    updateQuery.put("$set", new BasicDBObject("account", currentAmount));

    connection.getCollection("user").update(updateQuery, updateCommand);

  }

  private String transactionType(String deposit) {
    return deposit;
  }

  private float amount(float amount) {
    return amount;
  }

  private float currentAmount(float currentAmount) {
    return currentAmount;
  }

  private int userId(int userId) {
    return userId;
  }
}
