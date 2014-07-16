package com.clouway.persistent;

import com.clouway.core.BankMessage;
import com.clouway.core.Clock;
import com.clouway.core.DBMessage;
import com.clouway.core.Time;
import com.clouway.core.Transaction;
import com.clouway.util.BankUtil;
import com.google.inject.util.Providers;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
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

  private BankUtil bankUtil;

  private Clock clock = new Time();

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private DBMessage messagesDB = null;

  @Mock
  private BankMessage bankMessage = null;

  @Before
  public void setUp() throws UnknownHostException {

    MongoClient client = new MongoClient();

    connection = client.getDB("Bank_Test");

    cleanDB();

    bankUtil = new BankUtil(connection);

    persistentBankRepository = new PersistentBankRepository(Providers.of(connection), bankMessage, messagesDB);
  }



  @Test
  public void depositSameValue() throws Exception {
    pretendThatDeposit(amount(30), currentAmount(50), transactionType("deposit"), userId(23));

    expectInvoke();

    persistentBankRepository.makeTransaction(transaction);

    float currentAmount = persistentBankRepository.getCurrentAmount(userId(23));

    assertThat(currentAmount, is(80F));
  }

  private void cleanDB() {
    connection.getCollection("user_test").drop();
    connection.getCollection("transaction").drop();
  }

  private void expectInvoke() {
    context.checking(new Expectations() {{

      oneOf(bankMessage).deposit();
      will(returnValue("deposit"));

      oneOf(messagesDB).collectionUser();
      will(returnValue("user_test"));

      oneOf(messagesDB).fieldAccount();
      will(returnValue("account"));

      oneOf(messagesDB).collectionUser();
      will(returnValue("user_test"));

      oneOf(messagesDB).fieldId();
      will(returnValue("_id"));

      oneOf(messagesDB).operatorInc();
      will(returnValue("$inc"));

      oneOf(messagesDB).fieldAccount();
      will(returnValue("account"));

//      oneOf(messagesDB).fieldTransactionType();
//      will(returnValue("transactionType"));
//
//      oneOf(messagesDB).fieldAmount();
//      will(returnValue("amount"));
//
//      oneOf(messagesDB).fieldDate();
//      will(returnValue("date"));
//
//      oneOf(messagesDB).fieldUserId();
//      will(returnValue("user_id"));
//
//      oneOf(messagesDB).collectionTransaction();
//      will(returnValue("transaction"));

    }
    });
  }

  private void pretendThatDeposit(float amount, float currentAmount, String transactionType, int userId) {

    transaction = new Transaction(transactionType, amount, clock.now(), userId);

    bankUtil.deposit(transaction, currentAmount);

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
