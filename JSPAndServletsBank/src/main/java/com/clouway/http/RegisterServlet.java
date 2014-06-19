package com.clouway.http;

import com.clouway.core.Clock;
import com.clouway.core.SessionID;
import com.clouway.core.SiteMap;
import com.clouway.core.User;
import com.clouway.core.UserDAO;
import com.clouway.core.UserMessages;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by clouway on 6/13/14.
 */
@Singleton
public class RegisterServlet extends HttpServlet {

  private final SiteMap siteMap;
  private final UserMessages userMessages;
  private final UserDAO userDAO;
  private final Clock clock;

  @Inject
  public RegisterServlet(SiteMap siteMap,
                         UserMessages userMessages,
                         UserDAO userDAO,
                         Clock clock) {
    this.siteMap = siteMap;
    this.userMessages = userMessages;
    this.userDAO = userDAO;
    this.clock = clock;
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String userName = req.getParameter(userMessages.userName());

    String userPassword = req.getParameter(userMessages.userPassword());

    User user = userDAO.findUser(userName, userPassword);

    if (user == null) {

      SessionID session = userDAO.register(userName, userPassword, clock.now());

      Cookie cookie = new Cookie("sid", session.getSessionID());

      resp.addCookie(cookie);

      resp.sendRedirect(siteMap.mainPage());

      return;

    }

    resp.sendRedirect(siteMap.loginPage());
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

  }
}
