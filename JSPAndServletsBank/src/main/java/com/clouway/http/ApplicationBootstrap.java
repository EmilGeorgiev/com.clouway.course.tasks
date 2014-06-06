package com.clouway.http;

import com.clouway.core.CurrentUser;
import com.clouway.core.UserSessionsRepository;
import com.clouway.core.User;
import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.Provides;
import com.google.inject.servlet.RequestScoped;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by clouway on 6/2/14.
 */
public class ApplicationBootstrap extends AbstractModule {

  @Override
  protected void configure() {

  }

  @Provides
  @RequestScoped
  public CurrentUser getCurrentUser(Provider<HttpServletRequest> requestProvider, UserSessionsRepository userSessionsRepository) {
    Cookie[] cookies = requestProvider.get().getCookies();
    for (Cookie cookie : cookies) {
      // session id
      if ("sid".equalsIgnoreCase(cookie.getName())) {

        User user = userSessionsRepository.findUserAssociatedWithSession(cookie.getValue());
        return new CurrentUser(user);
      }
    }

    return new CurrentUser(null);
  }

}
