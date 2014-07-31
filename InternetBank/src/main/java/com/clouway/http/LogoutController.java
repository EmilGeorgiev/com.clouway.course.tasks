package com.clouway.http;

import com.clouway.core.SiteMap;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.sitebricks.At;
import com.google.sitebricks.headless.Reply;
import com.google.sitebricks.headless.Service;
import com.google.sitebricks.http.Get;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@At("/logoutController")
@Service
@Singleton
public class LogoutController {

  private final HttpServletRequest request;
  private final SiteMap siteMap;

  @Inject
  public LogoutController(HttpServletRequest request,
                          SiteMap siteMap) {

    this.request = request;
    this.siteMap = siteMap;
  }

  /**
   * Delete user' session from database.
   */
  @Get
  public Reply<?> logout() {

    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        cookie.setMaxAge(0);
      }
    }

    return Reply.saying().redirect(siteMap.loginController());
  }
}
