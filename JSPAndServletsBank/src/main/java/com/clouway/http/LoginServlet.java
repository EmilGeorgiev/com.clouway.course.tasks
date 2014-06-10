package com.clouway.http;

import com.clouway.core.BankAccountMessages;
import com.clouway.core.PageMessages;
import com.clouway.core.UserDAO;
import com.clouway.core.UserMessages;
import com.google.inject.Inject;
import com.google.inject.Provider;
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
  private final Provider<BankAccountMessages> bankAccountMessages;
  private final Provider<UserMessages> userMessagesProvider;
  private final Provider<PageMessages> pageMessagesProvider;


  @Inject
  public LoginServlet(UserDAO userDAO,
                      Provider<BankAccountMessages> bankAccountMessages,
                      Provider<UserMessages> userMessagesProvider,
                      Provider<PageMessages> pageMessagesProvider) {

    this.userDAO = userDAO;


    this.bankAccountMessages = bankAccountMessages;
    this.userMessagesProvider = userMessagesProvider;
    this.pageMessagesProvider = pageMessagesProvider;
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String userName = req.getParameter(bankAccountMessages.get().depositAmount());

    String userPassword = req.getParameter(userMessagesProvider.get().userPassword());

    if (userDAO.isUserExist(userName, userPassword)) {

      resp.sendRedirect(pageMessagesProvider.get().mainPage());

    }
  }
}
