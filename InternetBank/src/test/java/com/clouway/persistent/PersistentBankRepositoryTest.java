package com.clouway.persistent;

import com.clouway.core.Clock;
import com.clouway.core.Time;
import com.clouway.core.Transaction;
import com.clouway.core.TransactionEntity;
import com.clouway.core.TransactionMessages;
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

public class PersistentBankRepositoryTest {

  private PersistentBankRepository persistentBankRepository;

  private Transaction transaction;

  private TransactionEntity transactionEntity;

  private DB connection;

  private BankUtil bankUtil;

  private Clock clock = new Time();


  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private TransactionMessages transactionMessages = null;

  @Before
  public void setUp() throws UnknownHostException {

    MongoClient client = new MongoClient();

    connection = client.getDB("Bank_Test");

    cleanDB();

    transaction = new Transaction(type("deposit"), amount(10), clock.now(), userName("test"));

    transactionEntity = new TransactionEntity(type("deposit"), userName("test"),
            amount(10), clock.now());

    bankUtil = new BankUtil(connection);

    persistentBankRepository = new PersistentBankRepository(Providers.of(connection),
                                                            transactionMessages);

    bankUtil.registerUser(userName("test"));

  }

  @Test
  public void whenMakeTransferThenCreateAndNewTransaction() throws Exception {

    Transaction deposit = new Transaction(type("deposit"), amount(30), clock.now(), userName("test"));

    pretendThatHasTransactions(deposit);

    context.checking(new Expectations() {{
      oneOf(transactionMessages).success();
      will(returnValue("Transaction is success"));


    }
    });

    persistentBankRepository.updateBalance(transactionEntity);

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
