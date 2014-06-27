package com.clouway.http;

import com.clouway.core.*;
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
  private final ValidationUserData validationUserData;
  private final BankAccountMessages bankAccountMessages;

  @Inject
  public RegisterServlet(SiteMap siteMap,
                         UserMessages userMessages,
                         UserDAO userDAO,
                         Clock clock,
                         ValidationUserData validationUserData,
                         BankAccountMessages bankAccountMessages) {
    this.siteMap = siteMap;
    this.userMessages = userMessages;
    this.userDAO = userDAO;
    this.clock = clock;
    this.validationUserData = validationUserData;
    this.bankAccountMessages = bankAccountMessages;
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String userName = req.getParameter(userMessages.userName());

    String userPassword = req.getParameter(userMessages.userPassword());

    if (!validateUserDate(userName, userPassword)) {
      resp.sendError(401, siteMap.registerError());
      return;
//      resp.sendRedirect(siteMap.loginPage());
    }

    SessionID session = userDAO.registerUserIfNotExist(userName, userPassword, clock.now());

    if (session != null) {

      Cookie cookie = new Cookie(bankAccountMessages.sid(), session.getSessionID());

      resp.addCookie(cookie);

      resp.sendRedirect(siteMap.mainServlet());

      return;

    }

    resp.sendRedirect(siteMap.loginPage());
  }

  private boolean validateUserDate(String userName, String userPassword) {

    String namePattern = validationUserData.userNameValidationPattern();
    String passwordPattern = validationUserData.passwordValidationPattern();

    return userName.matches(namePattern) && userPassword.matches(passwordPattern);

  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doPost(req, resp);
  }
}
