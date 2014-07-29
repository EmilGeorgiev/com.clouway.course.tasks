package com.clouway.http;

import com.clouway.core.SessionRepository;
import com.clouway.core.SiteMap;
import com.clouway.core.User;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.sitebricks.At;
import com.google.sitebricks.headless.Reply;
import com.google.sitebricks.headless.Service;
import com.google.sitebricks.http.Get;

/**
 * Created by clouway on 7/17/14.
 */
@At("/logoutController")
@Service
@Singleton
public class LogoutController {

  private final SessionRepository sessionRepository;
  private final Provider<User> currentUser;
  private final SiteMap siteMap;

  @Inject
  public LogoutController(SessionRepository sessionRepository,
                          Provider<User> currentUser,
                          SiteMap siteMap) {

    this.sessionRepository = sessionRepository;
    this.currentUser = currentUser;
    this.siteMap = siteMap;
  }

  /**
   * Delete user' session from database.
   */
  @Get
  public Reply<?> logout() {

    sessionRepository.deleteSessionByID(currentUser.get().getUserSession());

    return Reply.saying().redirect(siteMap.loginController());
  }
}
