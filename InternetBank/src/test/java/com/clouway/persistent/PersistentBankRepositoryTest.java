package com.clouway.persistent;

import com.clouway.core.*;
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

public class PersistentBankRepositoryTest {

  private PersistentBankRepository persistentBankRepository;

  private TransactionEntity transactionEntity;

  private DB connection;

  private BankUtil bankUtil;

  private Clock clock = new Time();

  private Map<String, TransactionFactory> factoryHashMap = new HashMap<String, TransactionFactory>();


  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private TransactionMessages transactionMessages = null;

  @Before
  public void setUp() throws UnknownHostException {

    MongoClient client = new MongoClient();

    connection = client.getDB("Bank_Test");

    cleanDB();

    transactionEntity = new TransactionEntity(type("deposit"), userName("test"), amount(10), clock.now());

    bankUtil = new BankUtil(connection);

    factoryHashMap.put("deposit", new DepositFactory());
    factoryHashMap.put("withdraw", new WithdrawFactory());

    persistentBankRepository = new PersistentBankRepository(Providers.of(connection),
                                                            transactionMessages);

    bankUtil.registerUser(userName("test"));

  }

  @Test
  public void whenMakeTransferThenCreateAndNewTransaction() throws Exception {

    TransactionEntity deposit = new TransactionEntity(type("deposit"), userName("test"), amount(30), clock.now());

    pretendThatHasTransactions(deposit);

    context.checking(new Expectations() {{
      oneOf(transactionMessages).success();
      will(returnValue("Transaction is success"));


    }
    });

    persistentBankRepository.updateBalance(transactionEntity);

    double currentAmount = persistentBankRepository.getCurrentAmount(userName("test"));

    List<TransactionEntity> transactionList = persistentBankRepository.getAllTransactionsBy("test");

    assertThat(currentAmount, is(40D));

    assertThat(transactionList, is(Arrays.asList(deposit, transactionEntity)));

  }

  private void pretendThatHasTransactions(TransactionEntity deposit) {

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
