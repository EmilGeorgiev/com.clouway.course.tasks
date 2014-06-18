package com.clouway.http;

import com.clouway.core.AccountBankDAO;
import com.clouway.core.BankAccountMessages;
import com.clouway.core.CurrentUser;
import com.clouway.core.SiteMap;
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
 * Created by clouway on 6/11/14.
 */
@Singleton
public class TransactionServlet extends HttpServlet{

  private final AccountBankDAO accountBankDAO;
  private final BankAccountMessages bankAccountMessages;
  private final SiteMap siteMap;
  private final Provider<CurrentUser> currentUser;

  @Inject
  public TransactionServlet(AccountBankDAO accountBankDAO,
                            BankAccountMessages bankAccountMessages,
                            SiteMap siteMap,
                            Provider<CurrentUser> currentUser) {

    this.accountBankDAO = accountBankDAO;
    this.bankAccountMessages = bankAccountMessages;
    this.siteMap = siteMap;
    this.currentUser = currentUser;
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    String transactionAmount = req.getParameter(bankAccountMessages.transactionAmount());

    Float amount;

    try {
      amount = Float.parseFloat(transactionAmount);
    } catch (NumberFormatException e) {
      resp.sendRedirect(siteMap.mainPage());
      return;
    }

    User user = currentUser.get().getUser();

    if (req.getParameter(bankAccountMessages.transactionType()).equals(bankAccountMessages.deposit())) {
      accountBankDAO.deposit(amount, user.getUserID());
    } else {
      accountBankDAO.withdraw(amount, user.getUserID());
    }

    resp.sendRedirect(siteMap.mainPage());


  }
}
