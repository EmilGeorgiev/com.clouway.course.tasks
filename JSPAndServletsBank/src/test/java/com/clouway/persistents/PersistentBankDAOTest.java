package com.clouway.persistents;

import com.clouway.core.Clock;
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
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PersistentBankDAOTest {

  private PersistentBankDAO persistentBankDAO;

  private UserUtil userUtil;

  Clock clock = new Clock() {
    @Override
    public java.util.Date now() {
      return CalendarUtil.getDate(2014, 6, 18, 10, 50);
    }
  };

  @Rule
  public DatastoreRule datastoreRule = new DatastoreRule();

  @Before
  public void setUp() throws SQLException {

    Connection connection = datastoreRule.getDataSource().getConnection();

    userUtil = new UserUtil(connection);

    persistentBankDAO = new PersistentBankDAO(Providers.of(connection), clock);
  }

  @Test
  public void whenMakeDepositsWithSameValueThanCurrentAmountIncreasesWithThisValue() throws Exception {
    pretendThatUserHasDepositOf(20, new User("test", "testPass", userId(50)));

    float currentAmount = persistentBankDAO.getCurrentUserBankAmount(userId(50));

    assertThat(currentAmount, is(20f));

  }

  @Test
  public void getTransactionsOnAllUsers() throws Exception {

    pretendThatUserHasDepositOf(100, new User("ivan", "ivanPass", userId(1)));
    pretendThatUserHasDepositOf(150, new User("test", "testPass", userId(2)));

    List<Transaction> transactions = persistentBankDAO.getAllTransactions();


    assertThat(transactions.get(0).toString(), is("2014-07-18 10:50:00.0 | 100.0 | deposit"));
    assertThat(transactions.get(1).toString(), is("2014-07-18 10:50:00.0 | 150.0 | deposit"));

  }

  @Test
  public void takeHistoryOnCurrentUser() throws Exception {

    pretendThatUserHasDepositOf(20, new User("test", "testPass", userId(1)));

    List<Transaction> transactions = persistentBankDAO.getUserHistory(userId(1));

    assertThat(transactions.get(0).toString(), is("2014-07-18 10:50:00.0 | 20.0 | deposit"));

  }

  @Test
  public void withdrawValueFromCurrentAccount() throws Exception {

    pretendThatUserHasDepositOf(50, new User("ivan", "ivanPass", userId(23)));

    pretendThatUserHasWithdrawOf(20, userId(23));

    float currentAmount = persistentBankDAO.getCurrentUserBankAmount(userId(23));

    assertThat(currentAmount, is(30.0f));

  }

  private void pretendThatUserHasWithdrawOf(int amount, int userID) {
    persistentBankDAO.withdraw(amount, userID);
  }

  private int userId(int userId) {
    return userId;
  }

  private void pretendThatUserHasDepositOf(float deposit, User user) {
    userUtil.registerNewUser(user);

    persistentBankDAO.deposit(deposit, user.getUserID());
  }
}
