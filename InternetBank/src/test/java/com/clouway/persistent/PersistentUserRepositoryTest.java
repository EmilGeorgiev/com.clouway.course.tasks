package com.clouway.persistent;

import com.clouway.core.Clock;
import com.clouway.core.User;
import com.clouway.core.UserDTO;
import com.clouway.core.UserMessage;
import com.clouway.util.BankUtil;
import com.clouway.util.CalendarUtil;
import com.clouway.util.SessionUtil;
import com.google.inject.util.Providers;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.junit.Before;
import org.junit.Test;

import java.net.UnknownHostException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class PersistentUserRepositoryTest {

  private PersistentUserRepository userRepository;
  private DB connection;
  private CalendarUtil clock = new CalendarUtil(2014, 7, 28, 12, 20, 45 );
  private UserDTO userDTO;
  private BankUtil bankUtil;
  private SessionUtil sessionUtil;

  private UserMessage userMessage = new UserMessage() {


    @Override
    public String failed() {
      return "Registered is failed";
    }

    @Override
    public String success() {
      return "Registered is success";
    }
  };


  @Before
  public void setUp() throws UnknownHostException {
    MongoClient mongoClient = new MongoClient();

    connection = mongoClient.getDB("Bank_Test");

    userRepository = new PersistentUserRepository(Providers.of(connection),
                                                  clock,
                                                  Providers.of(userMessage));

    cleanDB();

    bankUtil = new BankUtil(connection);
    sessionUtil = new SessionUtil(connection);
  }

  @Test
  public void registerNewUser() throws Exception {

    pretendThatUserWantsRegister(name("test"), pass("userPass"));

    String userMessage = userRepository.registerUserIfNotExist(userDTO);

    assertThat(userMessage, is("Registered is success"));

  }

  @Test
  public void registerUserWhoAlreadyExist() throws Exception {

    pretendThatUserAlreadyIsRegistered(name("ivan"), pass("ivanTest"));

    String userMessage = userRepository.registerUserIfNotExist(userDTO);

    assertThat(userMessage, is("Registered is failed"));

  }

  @Test
  public void userSessionIsNotExpiration() throws Exception {

    pretendThatUserSessionIs(session("123"), userId("321"), expirationDate(new CalendarUtil(2014, 7, 28, 12, 55, 0)));

    User user = userRepository.authenticateSession(session("123"), clock);

    assertNotNull(user);

  }

  @Test
  public void userSessionIsExpiration() throws Exception {
    pretendThatUserSessionIs(session("456"), userId("654"), expirationDate(new CalendarUtil(2014, 7, 28, 10, 20, 0)));

    User user = userRepository.authenticateSession(session("456"), clock);

    assertNull(user);

  }

  private void pretendThatUserAlreadyIsRegistered(String name, String pass) {
    userDTO = new UserDTO(name, pass);
    bankUtil.registerUser(name, pass);
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


  private void pretendThatUserWantsRegister(String name, String pass) {
    userDTO = new UserDTO(name, pass);
  }

  private String pass(String pass) {
    return pass;
  }

  private String name(String name) {
    return name;
  }

  private void cleanDB() {
    connection.getCollection("users").drop();
    connection.getCollection("sessions").drop();
  }
}
