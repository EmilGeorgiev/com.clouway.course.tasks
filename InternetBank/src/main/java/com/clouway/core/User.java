package com.clouway.core;

/**
 * Created by clouway on 7/15/14.
 */
public class User {

  private final String userName;
  private final String sessionID;

  public User(String sessionID, String userName) {

    this.userName = userName;
    this.sessionID = sessionID;
  }

  public String getUserName() {
    return userName;
  }

  public String getUserSession() {
    return sessionID;
  }
}
