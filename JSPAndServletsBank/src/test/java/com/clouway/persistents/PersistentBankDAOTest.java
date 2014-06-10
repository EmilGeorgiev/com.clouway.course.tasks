package com.clouway.persistents;

import com.google.inject.Provider;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by clouway on 6/3/14.
 */
public class PersistentBankDAOTest {

  private PersistentBankDAO persistentBankDAO;

  private Provider<Connection> connectionProvider;

  @Rule
  public DataStoreRule dataStoreRule = new DataStoreRule();

  @Before
  public void setUp() throws SQLException {

    connectionProvider = new Provider<Connection>() {
      @Override
      public Connection get() {
        Connection connection = null;

        try {
          connection = dataStoreRule.getDataSource().getConnection();
        } catch (SQLException e) {
          e.printStackTrace();
        }

        return connection;
      }
    };
    persistentBankDAO = new PersistentBankDAO(connectionProvider);
  }

  @Test
  public void whenMakeDepositThanBankAccountIncreases() throws Exception {



  }
}
