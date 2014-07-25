package com.clouway.persistent;

import com.clouway.core.UserDTO;
import com.clouway.util.BankUtil;
import com.clouway.util.CalendarUtil;
import com.google.inject.util.Providers;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.junit.Before;
import org.junit.Test;

import java.net.UnknownHostException;

import static com.clouway.core.DBMessages.REGISTRATION_FAILED;
import static com.clouway.core.DBMessages.REGISTRATION_SUCCESS;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by clouway on 7/17/14.
 */
public class PersistentUserRepositoryTest {

  private PersistentUserRepository userRepository;
  private DB connection;
  private CalendarUtil clock = new CalendarUtil(2014, 7, 28, 12, 20, 45 );
  private UserDTO userDTO;
  private BankUtil bankUtil;


  @Before
  public void setUp() throws UnknownHostException {
    MongoClient mongoClient = new MongoClient();

    connection = mongoClient.getDB("Bank_Test");

    userRepository = new PersistentUserRepository(Providers.of(connection), clock);

    cleanDB();

    bankUtil = new BankUtil(connection);
  }



  @Test
  public void registerNewUser() throws Exception {

    pretendThatUserWantsRegister(name("test"), pass("userPass"));

    String userMessage = userRepository.registerUserIfNotExist(userDTO);

    assertThat(userMessage, is(REGISTRATION_SUCCESS));

  }

  @Test
  public void registerUserWhoAlreadyExist() throws Exception {

    pretendThatUserAlreadyIsRegistered(name("ivan"), pass("ivanTest"));

    String userMessage = userRepository.registerUserIfNotExist(userDTO);

    assertThat(userMessage, is(REGISTRATION_FAILED));

  }

  private void pretendThatUserAlreadyIsRegistered(String name, String pass) {
    userDTO = new UserDTO(name, pass);
    bankUtil.registerUser(name, pass);
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
  }
}
