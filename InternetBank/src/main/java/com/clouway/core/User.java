package com.clouway.core;

/**
 * Created by clouway on 7/15/14.
 */
public class User {

  private final String name;
  private final String password;
  private final Object userId;
  private final String sessionID;

  public User(String name, String password, Object userId, String sessionID) {

    this.name = name;
    this.password = password;
    this.userId = userId;
    this.sessionID = sessionID;
  }

  public String getName() {
    return name;
  }

  public String getPassword() {
    return password;
  }

  public Object getUserId() {
    return userId;
  }

  public String getUserSession() {
    return sessionID;
  }
}
