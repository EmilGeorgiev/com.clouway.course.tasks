package com.clouway.core;

/**
 * Created by clouway on 7/31/14.
 */
public class Session {

  private final String sessionID;
  private final String userName;

  public Session(String sessionID, String userName) {
    this.sessionID = sessionID;
    this.userName = userName;
  }

  public String getSessionID() {
    return sessionID;
  }

  public String getUserName() {
    return userName;
  }
}
