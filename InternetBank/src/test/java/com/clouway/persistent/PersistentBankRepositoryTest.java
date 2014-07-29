package com.clouway.persistent;

import com.clouway.core.BankTransaction;
import com.clouway.core.Clock;
import com.clouway.core.Time;
import com.clouway.core.Transaction;
import com.clouway.core.UserMessage;
import com.clouway.http.Deposit;
import com.clouway.http.Withdraw;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

  private Map<String, BankTransaction> bankTransactionMap = new HashMap<String, BankTransaction>();


  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private UserMessage userMessage = null;

  @Before
  public void setUp() throws UnknownHostException {

    MongoClient client = new MongoClient();

    connection = client.getDB("Bank_Test");

    cleanDB();

    transaction = new Transaction(type("deposit"), amount(10), clock.now(), userName("test"));

    bankUtil = new BankUtil(connection);

    bankTransactionMap.put("deposit", new Deposit(connection));
    bankTransactionMap.put("withdraw", new Withdraw(connection));

    persistentBankRepository = new PersistentBankRepository(Providers.of(connection),
    userMessage,
    Providers.of(bankTransactionMap));

    bankUtil.registerUser(userName("test"));

  }

  @Test
  public void whenMakeTransferThenCreateAndNewTransaction() throws Exception {

    Transaction deposit = new Transaction(type("deposit"), amount(30), clock.now(), userName("test"));

    pretendThatHasTransactions(deposit);

    context.checking(new Expectations() {{
      oneOf(userMessage).success();
      will(returnValue("success"));
    }
    });

    persistentBankRepository.executeTransaction(transaction);

    double currentAmount = persistentBankRepository.getCurrentAmount(userName("test"));

    List<Transaction> transactionList = persistentBankRepository.getAllTransactionsBy("test");

    assertThat(currentAmount, is(40D));

    assertThat(transactionList, is(Arrays.asList(deposit, transaction)));

  }

  private void pretendThatHasTransactions(Transaction deposit) {

    bankUtil.makeTransaction(deposit);

  }

  private String type(String transferType) {
    return transferType;
  }

  private double amount(double amount) {

    return amount;
  }

  private String userName(String userId) {
    return userId;
  }

  private void cleanDB() {
    connection.getCollection("users").drop();
    connection.getCollection("transactions").drop();
  }
}
