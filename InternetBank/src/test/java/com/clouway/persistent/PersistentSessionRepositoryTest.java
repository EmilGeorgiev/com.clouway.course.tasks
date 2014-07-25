package com.clouway.persistent;

import com.clouway.core.Clock;
import com.clouway.core.SessionRepository;
import com.clouway.core.User;
import com.clouway.util.BankUtil;
import com.clouway.util.CalendarUtil;
import com.clouway.util.SessionUtil;
import com.google.inject.util.Providers;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.junit.Before;
import org.junit.Test;

import java.net.UnknownHostException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by clouway on 7/24/14.
 */
public class PersistentSessionRepositoryTest {

  private SessionRepository sessionRepository;
  private DB connection;
  private BankUtil bankUtil;
  private Clock clock = new CalendarUtil(2014, 7, 24, 10, 30, 45);
  private SessionUtil sessionUtil;

  @Before
  public void setUp() throws UnknownHostException {
    MongoClient mongoClient = new MongoClient();

    connection = mongoClient.getDB("Bank_Test");

    sessionRepository = new PersistentSessionRepository(Providers.of(connection), clock);

    sessionUtil = new SessionUtil(connection);

    cleanDB();
  }

  private void cleanDB() {
    connection.getCollection("sessions").drop();
  }

  @Test
  public void userSessionIsNotExpiration() throws Exception {

    pretendThatUserSessionIs(session("123"), userId("321"), expirationDate(new CalendarUtil(2014, 7, 24, 11, 5, 0)));

    User user = sessionRepository.authenticateSession(session("123"), clock);

    assertNotNull(user);

  }

  @Test
  public void userSessionIsExpiration() throws Exception {
    pretendThatUserSessionIs(session("456"), userId("654"), expirationDate(new CalendarUtil(2014, 7, 24, 10, 20, 0)));

    User user = sessionRepository.authenticateSession(session("456"), clock);

    assertNull(user);

  }

  private String userId(String userId) {
    return userId;
  }

  private void pretendThatUserSessionIs(String session, String userId, Clock expirationTime) {
    sessionUtil.addUserSessionInDatabase(session, userId, expirationTime);
  }

  private Clock expirationDate(CalendarUtil calendarUtil) {
    return calendarUtil;
  }

  private String session(String session) {
    return session;
  }
}
