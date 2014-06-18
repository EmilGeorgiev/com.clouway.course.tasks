package com.clouway.persistents;

import com.clouway.core.Clock;
import com.clouway.core.SessionID;
import com.clouway.core.Time;
import com.clouway.core.User;
import com.google.inject.util.Providers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by clouway on 6/12/14.
 */
public class PersistentUserDAOTest {

  private PersistentUserDAO persistentUserDAO;

  private User user;

  private Clock clock;


  @Rule
  public DatastoreRule datastoreRule = new DatastoreRule();

  @Before
  public void setUp() throws SQLException {

    Connection connection = datastoreRule.getDataSource().getConnection();

    clock = new Time();

    user = new User("emil", "emil", 1);


    persistentUserDAO = new PersistentUserDAO(Providers.of(connection), clock);
    persistentUserDAO.register(user.getUserName(), user.getPassword());

  }

  @Test
  public void authenticateNewUser() throws Exception {

    persistentUserDAO.register("ivan", "ivan");

    // registers new session
    SessionID sessionID = persistentUserDAO.authenticate("ivan", "ivan"/* , 2014-05-20 10:50*/);

    persistentUserDAO.isValidUserSession(sessionID.getSessionID()/* 2014-05-20 11:25 */);

    assertThat(sessionID.getSessionID(), notNullValue());

  }

  @Test
  public void validUserSessionExpired() throws Exception {

    clock.setTimeExpiry(30);

    User ivan = new User("ivan", "ivan", 2);

    persistentUserDAO.register(ivan.getUserName(), ivan.getPassword());

    boolean isValidSession = persistentUserDAO.isValidUserSession("");//Tuk trqnba da pomislq

    assertThat(isValidSession, is(true));

  }

  @Test
  public void expiredUserSession() throws Exception {
    clock.setTimeExpiry(0);

    boolean isValidSession = persistentUserDAO.isValidUserSession(""/*, clock.now()*/); //tuk syshto trqbva da pomislq

    assertThat(isValidSession, is(false));

  }
}
