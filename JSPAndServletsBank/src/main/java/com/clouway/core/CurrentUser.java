package com.clouway.core;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Created by clouway on 6/2/14.
 */
@Singleton
public class CurrentUser {

  private final User user;

  @Inject
  public CurrentUser(User user) {
    this.user = user;
  }

  public User getUser() {
    return user;
  }
}
