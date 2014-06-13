package com.clouway.persistents;

import com.clouway.core.Clock;
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

public class PersistentBankDAOTest {

  private PersistentBankDAO persistentBankDAO;

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

    Connection connection = dataStoreRule.getDataSource().getConnection();

    userUtil = new UserUtil(connection);

    userUtil.registerNewUser(user);

    persistentBankDAO = new PersistentBankDAO(Providers.of(connection), depositListener, clock);

    persistentBankDAO.deposit(100, user.getUserID());
    persistentBankDAO.deposit(150, user.getUserID());
  }

//  @Test
//  public void whenMakeDepositWithSomeValueThanBankAccountIncreasesWithThisValue() throws Exception {
//
//    persistentBankDAO.deposit(100, user.getUserID());
//
//    float currentAmount = persistentBankDAO.getCurrentUserBankAmount(user.getUserID());
//
//    assertThat(currentAmount, is(100f));
//
//  }

  @Test
  public void whenMakeTwoDepositsWhitSameValueThanCurrentAmountIncreasesTwoTime() throws Exception {

    float currentAmount = persistentBankDAO.getCurrentUserBankAmount(user.getUserID());

    assertThat(currentAmount, is(250f));

  }

  @Test
  public void whenMakeTwoDepositsThenMakeAndTwoTransaction() throws Exception {

    List<Transaction> transactions = persistentBankDAO.getAllTransactions();

    assertThat(transactions.get(0).toString(), is("2014-02-11 00:00:00.0 | 100.0 | deposit"));
    assertThat(transactions.get(1).toString(), is("2014-02-11 00:00:00.0 | 150.0 | deposit"));

  }

  @Test
  public void takeHistoryOnCurrentUser() throws Exception {
    User ivan = new User("ivan", "ivan", 2, "XCL453");

    userUtil.registerNewUser(ivan);

    persistentBankDAO.deposit(200, ivan.getUserID());

    List<Transaction> transactions = persistentBankDAO.getUserHistory(ivan.getUserID());

    assertThat(transactions.get(0).toString(), is("2014-02-11 00:00:00.0 | 200.0 | deposit"));

  }

  @Test
  public void whenWithdrawingSomeValueThanCurrentAccountDecrementWithThisValue() throws Exception {

    persistentBankDAO.withdrawing(30, user.getUserID());

    float currentAmount = persistentBankDAO.getCurrentUserBankAmount(user.getUserID());

    assertThat(currentAmount, is(220.0f));

  }
}
