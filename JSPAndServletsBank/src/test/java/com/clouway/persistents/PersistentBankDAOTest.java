package com.clouway.persistents;

import com.clouway.core.Clock;
import com.clouway.core.CurrentUser;
import com.clouway.core.DepositListener;
import com.clouway.core.Listener;
import com.clouway.core.Transaction;
import com.clouway.core.User;
import com.clouway.persistents.util.CalendarUtil;
import com.clouway.persistents.util.UserUtil;
import com.google.inject.util.Providers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

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

  private DepositListener depositListener = new Listener();

  Clock clock = new Clock() {
    @Override
    public Timestamp now() {
      return new Timestamp(CalendarUtil.february(2014, 1, 11));
    }
  };

  @Rule
  public DataStoreRule dataStoreRule = new DataStoreRule();

  @Before
  public void setUp() throws SQLException {

    currentUser = new CurrentUser(user);

    connection = dataStoreRule.getDataSource().getConnection();

    userUtil = new UserUtil(connection);

    userUtil.registerNewUser(user.getUserName(), user.getPassword());

    persistentBankDAO = new PersistentBankDAO(Providers.of(connection), Providers.of(currentUser), depositListener, clock);
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

  @Test
  public void whenMakeTwoDepositsThenMakeAndTwoTransaction() throws Exception {
    persistentBankDAO.deposit(100, user.getUserID());
    persistentBankDAO.deposit(150, user.getUserID());

    List<Transaction> transactions = persistentBankDAO.getAllTransactions();

    //System.out.println(transactions.get(0).getDateTransaction());

    assertThat(transactions, is(transactions));

  }
}
