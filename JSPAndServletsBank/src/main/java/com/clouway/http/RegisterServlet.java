package com.clouway.http;

import com.clouway.core.AuthenticateService;
import com.clouway.core.SiteMap;
import com.clouway.core.UserDAO;
import com.clouway.core.UserMessages;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by clouway on 6/13/14.
 */
@Singleton
public class RegisterServlet extends HttpServlet {

  private final UserDAO userDAO;
  private final SiteMap siteMap;
  private final UserMessages userMessages;
  private final AuthenticateService authenticateService;

  @Inject
  public RegisterServlet(UserDAO userDAO,
                         SiteMap siteMap,
                         UserMessages userMessages,
                         AuthenticateService authenticateService) {

    this.userDAO = userDAO;
    this.siteMap = siteMap;
    this.userMessages = userMessages;
    this.authenticateService = authenticateService;
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String userName = req.getParameter(userMessages.userName());

    String userPassword = req.getParameter(userMessages.userPassword());


    if (authenticateService.authenticate(userName, userPassword) == null) {
      userDAO.register(userName, userPassword);

      resp.sendRedirect(siteMap.mainPage());

      return;

    }

    resp.sendRedirect(siteMap.loginPage());
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

  }
}
