package com.clouway.http;

import com.clouway.core.BankAccountMessages;
import com.clouway.core.CurrentUser;
import com.clouway.core.AccountBankDAO;
import com.clouway.core.PageMessages;
import com.clouway.core.User;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by clouway on 5/28/14.
 */
@Singleton
public class DepositAccountServlet extends HttpServlet{

  private final AccountBankDAO accountBankDAO;

  private final BankAccountMessages bankAccountMessages;
  private final Provider<CurrentUser> currentUserProvider;
  private final PageMessages pageMessagesProvider;

  @Inject
  public DepositAccountServlet(AccountBankDAO accountBankDAO,
                               BankAccountMessages bankAccountMessages,
                               Provider<CurrentUser> currentUserProvider,
                               PageMessages pageMessagesProvider) {

    this.accountBankDAO = accountBankDAO;
    this.bankAccountMessages = bankAccountMessages;
    this.currentUserProvider = currentUserProvider;
    this.pageMessagesProvider = pageMessagesProvider;
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    //Get constant for deposit and than getUser parameter from request.
    String amount = req.getParameter(bankAccountMessages.depositAmount());

    Integer depositAmount;

    try {

      depositAmount = Integer.parseInt(amount);

    } catch (NumberFormatException ex) {

      resp.sendRedirect(pageMessagesProvider.login());
      return;
    }

    User user = currentUserProvider.get().getUser();

    //Deposit some value <parameter>depositAmount<parameter> in account of user with <parameter>userID<parameter>
    accountBankDAO.deposit(depositAmount, user.getUserID());

    //Return back to main page.
    resp.sendRedirect(pageMessagesProvider.mainPage());
  }
}
