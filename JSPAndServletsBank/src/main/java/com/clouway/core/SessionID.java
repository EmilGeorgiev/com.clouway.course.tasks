package com.clouway.core;

import javax.servlet.http.Cookie;

/**
 * Created by clouway on 6/11/14.
 */
public class SessionID {

  private final String sessionID;
  private Cookie cookie;

  public SessionID(String sessionID) {
    this.sessionID = sessionID;
    setCookie(sessionID);
  }

  public String getSessionID() {
    return sessionID;
  }

  public Cookie getCookie() {
    return cookie;
  }

  private void setCookie(String sessionID) {
    this.cookie = new Cookie("sessionID", sessionID);
  }
}
