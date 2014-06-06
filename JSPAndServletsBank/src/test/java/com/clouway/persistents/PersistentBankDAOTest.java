package com.clouway.persistents;

import com.clouway.core.AccountBankDAO;
import com.clouway.core.TransactionHistory;
import com.google.inject.Provider;
import org.jmock.auto.Mock;
import org.junit.Before;
import org.junit.Rule;

import java.sql.Connection;

/**
 * Created by clouway on 6/3/14.
 */
public class PersistentBankDAOTest {

  private PersistentBankDAO persistentBankDAO;

  @Rule
  public DataStoreRule dataStoreRule = new DataStoreRule();

  @Mock
  private AccountBankDAO accountBankDAO;

  @Mock
  private TransactionHistory transactionHistory;

  @Mock
  private Provider<Connection> connectionProvider;

  @Before
  public void setUp() {
    persistentBankDAO = new PersistentBankDAO(connectionProvider);
  }
}
