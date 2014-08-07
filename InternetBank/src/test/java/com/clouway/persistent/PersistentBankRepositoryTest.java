package com.clouway.persistent;

import com.clouway.core.Clock;
import com.clouway.core.CurrentUser;
import com.clouway.core.SystemClock;
import com.clouway.core.TransactionEntity;
import com.clouway.core.TransactionInfo;
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
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PersistentBankRepositoryTest {

  private PersistentBankRepository persistentBankRepository;

  private DB connection;

  private BankUtil bankUtil;

  private Clock clock = new SystemClock();


  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private TransactionMessages transactionMessages = null;

  @Before
  public void setUp() throws UnknownHostException {

    MongoClient client = new MongoClient();

    connection = client.getDB("Bank_Test");

    cleanDB();

    CurrentUser currentUser = new CurrentUser(userName("test"));

    bankUtil = new BankUtil(connection, userName("test"), clock);

    bankUtil.register(userName("test"));

    persistentBankRepository = new PersistentBankRepository(Providers.of(connection),
            transactionMessages,
            Providers.of(currentUser),
            clock);

  }

  @Test
  public void whenMakeTransferThenCreateAndNewTransaction() throws Exception {

    pretendThatHasDepositTransactions(amount(50));

    pretendThatHasWithdrawTransaction(amount(30));

    context.checking(new Expectations() {
      {
        oneOf(transactionMessages).success();
        will(returnValue("Transaction is success."));


      }
    });

    TransactionInfo info = persistentBankRepository.deposit(20.0);

    List<TransactionEntity> transactions = persistentBankRepository.getUserTransactions();

    Double currentAmount = persistentBankRepository.getCurrentAmount();

    assertThat(currentAmount, is(40.0));

    assertThat(info.getMessage(), is("Transaction is success."));

    assertThat(transactions.size(), is(3));

  }

  private void pretendThatHasWithdrawTransaction(double amount) {
    bankUtil.withdraw(amount);
  }

  private void pretendThatHasDepositTransactions(Double amount) {

    bankUtil.deposit(amount);

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
