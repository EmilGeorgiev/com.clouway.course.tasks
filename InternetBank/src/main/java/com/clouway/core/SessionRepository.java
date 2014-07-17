package com.clouway.core;

/**
 * Created by clouway on 7/16/14.
 */
public interface SessionRepository {
  boolean authenticateSession(String sessionID);

  User isUserExistBySession(String value, Clock clock);

  String authenticateUser(UserDTO userDTO);
}
