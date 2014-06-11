package com.clouway.http;

import com.clouway.core.PageMessages;
import com.clouway.core.UserDAO;
import com.clouway.core.UserMessages;
import com.clouway.core.UserSessionsRepository;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by clouway on 5/30/14.
 */
@Singleton
public class LoginServlet extends HttpServlet {


  private final UserDAO userDAO;
  private final UserMessages userMessages;
  private final PageMessages pageMessages;
  private final UserSessionsRepository userSessionsRepository;

  @Inject
  public LoginServlet(UserDAO userDAO, UserMessages userMessages, PageMessages pageMessages, UserSessionsRepository userSessionsRepository) {

    this.userDAO = userDAO;
    this.userMessages = userMessages;
    this.pageMessages = pageMessages;
    this.userSessionsRepository = userSessionsRepository;
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String userName = req.getParameter(userMessages.userName());

    String userPassword = req.getParameter(userMessages.userPassword());

    if (userDAO.isUserExist(userName, userPassword)) {

      resp.sendRedirect(pageMessages.mainPage());

    } else {
      resp.sendRedirect(pageMessages.loginPage());
    }
  }
}
