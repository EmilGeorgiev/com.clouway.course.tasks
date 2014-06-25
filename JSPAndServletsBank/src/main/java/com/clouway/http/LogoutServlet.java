package com.clouway.http;

import com.clouway.core.BankAccountMessages;
import com.clouway.core.SiteMap;
import com.clouway.core.UserSessionsRepository;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * When user is logout then hes session and <code>Cookie</code> is deleted from database.
 */
@Singleton
public class LogoutServlet extends HttpServlet {


  private final UserSessionsRepository userSessionsRepository;
  private final SiteMap siteMap;
  private final BankAccountMessages bankAccountMessages;


  @Inject
  public LogoutServlet(UserSessionsRepository userSessionsRepository,
                       SiteMap siteMap,
                       BankAccountMessages bankAccountMessages) {

    this.userSessionsRepository = userSessionsRepository;
    this.siteMap = siteMap;

    this.bankAccountMessages = bankAccountMessages;
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    String sessionID = getSessionID(req);

    if (sessionID != null) {
      userSessionsRepository.deleteSession(sessionID);
    }

    resp.sendRedirect(siteMap.loginPage());
  }

  private String getSessionID(HttpServletRequest req) {
    Cookie[] cookies = req.getCookies();

    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if ( bankAccountMessages.sid().equals(cookie.getName())) {
          return cookie.getValue();
        }
      }
    }

    return null;
  }
}
