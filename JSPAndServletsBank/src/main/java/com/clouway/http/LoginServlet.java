package com.clouway.http;

import com.clouway.core.AuthenticateService;
import com.clouway.core.BankAccountMessages;
import com.clouway.core.Clock;
import com.clouway.core.SessionID;
import com.clouway.core.SiteMap;
import com.clouway.core.UserMessages;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class LoginServlet extends HttpServlet {

  private final UserMessages userMessages;
  private final SiteMap siteMap;
  private final AuthenticateService authenticateService;
  private final Clock clock;
  private final BankAccountMessages bankAccountMessages;


  @Inject
  public LoginServlet(UserMessages userMessages,
                      SiteMap siteMap,
                      AuthenticateService authenticateService,
                      Clock clock,
                      BankAccountMessages bankAccountMessages) {

    this.userMessages = userMessages;
    this.siteMap = siteMap;

    this.authenticateService = authenticateService;
    this.clock = clock;

    this.bankAccountMessages = bankAccountMessages;
  }


  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doPost(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String userName = req.getParameter(userMessages.userName());

    String userPassword = req.getParameter(userMessages.userPassword());

    SessionID sessionID = authenticateService.authenticate(userName, userPassword, clock.now());

    if (sessionID != null) {
      Cookie sessionCookie = new Cookie(bankAccountMessages.sid(), sessionID.getSessionID());
      resp.addCookie(sessionCookie);

     resp.sendRedirect(siteMap.mainServlet());

    } else {
      resp.sendRedirect(siteMap.loginPage());
    }
  }
}
