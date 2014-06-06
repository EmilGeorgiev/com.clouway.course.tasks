package com.clouway.http;

import com.clouway.core.BankAccountMessages;
import com.clouway.core.Constants;
import com.clouway.core.UserDAO;
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
  private final BankAccountMessages bankAccountMessages;

  @Inject
  public LoginServlet(UserDAO userDAO, BankAccountMessages bankAccountMessages) {

    this.userDAO = userDAO;
    this.bankAccountMessages = bankAccountMessages;

  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String userName = req.getParameter(bankAccountMessages.userName());

    String userPassword = req.getParameter(bankAccountMessages.userPassword());

    if (userDAO.isUserExist(userName, userPassword)) {

      resp.sendRedirect(Constants.MAIN_PAGE);

    }
  }
}
