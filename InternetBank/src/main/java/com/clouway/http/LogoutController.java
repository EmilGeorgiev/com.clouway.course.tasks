package com.clouway.http;

import com.clouway.core.SessionRepository;
import com.clouway.core.User;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.sitebricks.At;
import com.google.sitebricks.http.Get;

/**
 * Created by clouway on 7/17/14.
 */
@At("/logoutController")
@Singleton
public class LogoutController {
  private final SessionRepository sessionRepository;
  private final Provider<User> currentUser;

  @Inject
  public LogoutController(SessionRepository sessionRepository, Provider<User> currentUser) {

    this.sessionRepository = sessionRepository;
    this.currentUser = currentUser;
  }

  @Get
  public void logout() {

    sessionRepository.deleteSessionByID(currentUser.get().getUserSession());

  }
}
