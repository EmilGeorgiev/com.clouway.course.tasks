package com.clouway.persistent;

import com.clouway.core.BankMessage;
import com.clouway.core.Clock;
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
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

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
  private BankMessage bankMessage = null;

  @Before
  public void setUp() throws UnknownHostException {

    MongoClient client = new MongoClient();

    connection = client.getDB("Bank_Test");

    cleanDB();

    transaction = new Transaction(type("deposit"), amount(10), clock.now(), userId("123"));

    bankUtil = new BankUtil(connection);

    bankUtil.registerUser(userId("123"));

    persistentBankRepository = new PersistentBankRepository(Providers.of(connection), bankMessage);
  }

  @Test
  public void whenMakeTransferThenCreateAndNewTransaction() throws Exception {

    Transaction deposit = new Transaction(type("deposit"), amount(30), clock.now(), userId("123"));

    Transaction withdraw = new Transaction(type("withdrawing"), amount(20), clock.now(), userId("123"));

    pretendThatHasTransactions(deposit, withdraw);

    context.checking(new Expectations() {{
      oneOf(bankMessage).deposit();
      will(returnValue("deposit"));
    }
    });

    persistentBankRepository.makeTransaction(transaction);

    double currentAmount = persistentBankRepository.getCurrentAmount(userId("123"));

    List<Transaction> transactionList = persistentBankRepository.getAllTransactionByUserName("123");

    assertThat(currentAmount, is(20D));

    assertThat(transactionList, is(Arrays.asList(deposit, withdraw, transaction)));

  }

  private void pretendThatHasTransactions(Transaction deposit, Transaction withdrawing) {

    bankUtil.makeTransaction(deposit);

    withdrawing.setAmount(-withdrawing.getAmount());
    bankUtil.makeTransaction(withdrawing);
  }

  private String type(String transferType) {
    return transferType;
  }

  private double amount(double amount) {

    return amount;
  }

  private String userId(String userId) {
    return userId;
  }

  private void cleanDB() {
    connection.getCollection("users").drop();
    connection.getCollection("transactions").drop();
  }
}
