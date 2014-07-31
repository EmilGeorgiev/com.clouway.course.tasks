package com.clouway.core;

/**
 * Represent domain object.
 */
public class User {

  private final String userName;
  private final String sessionID;

  public User(String sessionID, String userName) {

    this.userName = userName;
    this.sessionID = sessionID;
  }

  public String getName() {
    return userName;
  }

  public String getSession() {
    return sessionID;
  }
}
