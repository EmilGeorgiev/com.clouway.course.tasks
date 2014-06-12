package com.clouway.persistents;

import com.clouway.core.SessionID;
import com.clouway.core.User;
import com.clouway.core.UserDAO;
import com.clouway.core.UserSessionsRepository;

/**
 * Created by clouway on 6/12/14.
 */
public class PersistentUserDAO implements UserDAO, UserSessionsRepository {
  @Override
  public User getUser(String name, String password) {
    return null;
  }

  @Override
  public SessionID authenticate(String userName, String userPassword) {
    return null;
  }

  @Override
  public void register(String userName, String userPassword) {

  }

  @Override
  public User isUserExistBySession(String sessionId) {
    return null;
  }

  @Override
  public void deleteSession(int userID) {

  }

  @Override
  public boolean isValidUserSession(String value) {
    return false;
  }
}
