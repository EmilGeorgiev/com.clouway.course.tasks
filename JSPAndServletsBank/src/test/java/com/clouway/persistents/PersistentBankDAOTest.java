package com.clouway.persistents;

import com.clouway.core.CurrentUser;
import com.clouway.core.User;
import com.clouway.persistents.util.UserUtil;
import com.google.inject.util.Providers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by clouway on 6/3/14.
 */
public class PersistentBankDAOTest {

  private PersistentBankDAO persistentBankDAO;

  private Connection connection;

  private CurrentUser currentUser;

  private User user = new User("emil", "emil", 1, "XVL4567");

  private UserUtil userUtil;

  @Rule
  public DataStoreRule dataStoreRule = new DataStoreRule();

  @Before
  public void setUp() throws SQLException {

    currentUser = new CurrentUser(user);

    connection = dataStoreRule.getDataSource().getConnection();

    userUtil = new UserUtil(connection);

    userUtil.registerNewUser(user.getUserName(), user.getPassword());

    persistentBankDAO = new PersistentBankDAO(Providers.of(connection), Providers.of(currentUser));
  }

  @Test
  public void whenMakeDepositThanBankAccountIncreases() throws Exception {

    persistentBankDAO.deposit(100, user.getUserID());

    float currentAmount = persistentBankDAO.getCurrentUserBankAmount(user.getUserID());

    assertThat(currentAmount, is(100f));

  }

  @Test
  public void whenMakeTwoDepositsWhitSameValueThanCurrentAmountIncreasesTwoTime() throws Exception {

    persistentBankDAO.deposit(100, user.getUserID());
    persistentBankDAO.deposit(150, user.getUserID());

    float currentAmount = persistentBankDAO.getCurrentUserBankAmount(user.getUserID());

    assertThat(currentAmount, is(250f));

  }
}
