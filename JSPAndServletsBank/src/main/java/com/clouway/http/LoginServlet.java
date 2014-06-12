package com.clouway.http;

import com.clouway.core.PageSiteMap;
import com.clouway.core.SessionID;
import com.clouway.core.UserDAO;
import com.clouway.core.UserMessages;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class LoginServlet extends HttpServlet {


  private final UserDAO userDAO;
  private final UserMessages userMessages;
  private final PageSiteMap pageSiteMap;

  @Inject
  public LoginServlet(UserDAO userDAO, UserMessages userMessages, PageSiteMap pageSiteMap) {

    this.userDAO = userDAO;
    this.userMessages = userMessages;
    this.pageSiteMap = pageSiteMap;

  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String userName = req.getParameter(userMessages.userName());

    String userPassword = req.getParameter(userMessages.userPassword());

    SessionID sessionID = userDAO.authenticate(userName, userPassword);

    if (sessionID != null) {

      resp.addCookie(sessionID.getCookie());

      resp.sendRedirect(pageSiteMap.mainPage());

    } else {
      resp.sendRedirect(pageSiteMap.loginPage());
    }
  }
}
