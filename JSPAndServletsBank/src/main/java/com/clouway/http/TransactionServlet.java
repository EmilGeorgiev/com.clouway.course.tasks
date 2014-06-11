package com.clouway.http;

import com.clouway.core.AccountBankDAO;
import com.clouway.core.BankAccountMessages;
import com.clouway.core.CurrentUser;
import com.clouway.core.PageMessages;
import com.clouway.core.User;
import com.google.inject.Inject;
import com.google.inject.Provider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by clouway on 6/11/14.
 */
public class TransactionServlet extends HttpServlet{

  private final AccountBankDAO accountBankDAO;
  private final BankAccountMessages bankAccountMessages;
  private final PageMessages pageMessages;
  private final Provider<CurrentUser> currentUser;

  @Inject
  public TransactionServlet(AccountBankDAO accountBankDAO,
                            BankAccountMessages bankAccountMessages,
                            PageMessages pageMessages,
                            Provider<CurrentUser> currentUser) {

    this.accountBankDAO = accountBankDAO;
    this.bankAccountMessages = bankAccountMessages;
    this.pageMessages = pageMessages;
    this.currentUser = currentUser;
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    String transactionAmount = req.getParameter(bankAccountMessages.transactionAmount());

    Float amount = null;

    try {
      amount = Float.parseFloat(transactionAmount);
    } catch (NumberFormatException e) {
      resp.sendRedirect(pageMessages.mainPage());
      return;
    }

    User user = currentUser.get().getUser();

    if (req.getHeader("Referer").equals(pageMessages.depositPage())) {
      accountBankDAO.deposit(amount, user.getUserID());
    } else {
      accountBankDAO.withdrawing(amount, user.getUserID());
    }

    resp.sendRedirect(pageMessages.mainPage());


  }
}
