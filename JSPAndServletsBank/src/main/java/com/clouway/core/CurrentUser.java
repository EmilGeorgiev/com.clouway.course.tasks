package com.clouway.core;

/**
 * Created by clouway on 6/2/14.
 */
public class CurrentUser {

  private final User user;

  public CurrentUser(User user) {
    this.user = user;
  }

  public User get() {
    return user;
  }
}
