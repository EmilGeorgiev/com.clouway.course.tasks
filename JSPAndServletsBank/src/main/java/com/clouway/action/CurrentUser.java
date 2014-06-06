package com.clouway.action;

import com.clouway.objects.User;

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
