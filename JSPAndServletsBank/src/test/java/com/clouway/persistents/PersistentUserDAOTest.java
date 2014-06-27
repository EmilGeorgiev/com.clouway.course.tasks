package com.clouway.persistents;

import com.clouway.core.Clock;
import com.clouway.core.SessionID;
import com.clouway.core.Time;
import com.clouway.core.User;
import com.clouway.persistents.util.CalendarUtil;
import com.google.inject.util.Providers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by clouway on 6/12/14.
 */
public class PersistentUserDAOTest {

  private PersistentUserDAO persistentUserDAO;

  private Clock clock = new Clock() {
    @Override
    public Date now() {
      return CalendarUtil.getDate(2014, 6, 18, 10, 50);
    }
  };


  @Rule
  public DatastoreRule datastoreRule = new DatastoreRule();

  @Before
  public void setUp() throws SQLException {

    Connection connection = datastoreRule.getDataSource().getConnection();

    clock = new Time();

    User user = new User("emil", "emil", 1);

    persistentUserDAO = new PersistentUserDAO(Providers.of(connection));

    persistentUserDAO.registerUserIfNotExist(user.getUserName(), user.getPassword(), clock.now());

  }

  @Test
  public void authenticateNewUser() throws Exception {

    SessionID session = persistentUserDAO.registerUserIfNotExist("ivan", "ivanPass", clock.now());

    persistentUserDAO.isValidUserSession(session.getSessionID(), CalendarUtil.getDate(2014, 6, 18, 11, 25));

    assertThat(session.getSessionID(), notNullValue());

  }

  @Test
  public void validUserSessionExpired() throws Exception {

    User ivan = new User("ivan", "ivanPass", 2);

    SessionID session = persistentUserDAO.registerUserIfNotExist(ivan.getUserName(), ivan.getPassword(), CalendarUtil.getDate(2014, 6, 18, 10, 55));
    System.out.println(session);

    boolean isValidSession = persistentUserDAO.isValidUserSession(session.getSessionID(), CalendarUtil.getDate(2014, 6, 18, 11, 5));

    assertThat(isValidSession, is(true));

  }

  @Test
  public void expiredUserSession() throws Exception {

    boolean isValidSession = persistentUserDAO.isValidUserSession("", CalendarUtil.getDate(2014, 6, 18, 11, 25));

    assertThat(isValidSession, is(false));

  }
}
