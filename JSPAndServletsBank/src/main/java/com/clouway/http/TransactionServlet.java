package com.clouway.http;

import com.clouway.core.AccountBankDAO;
import com.clouway.core.BankAccountMessages;
import com.clouway.core.CurrentUser;
import com.clouway.core.SiteMap;
import com.clouway.core.User;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import javax.servlet.RequestDispatcher;
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

    Float transactionAmount;

    try {
      transactionAmount = Float.valueOf(req.getParameter(bankAccountMessages.transactionAmount()));
    } catch (NumberFormatException e) {
      resp.sendRedirect(siteMap.mainPage());
      return;
    }

    makeTransaction(req, transactionAmount);

    RequestDispatcher requestDispatcher = req.getRequestDispatcher(siteMap.mainPage());
    requestDispatcher.forward(req, resp);

  }

  private void makeTransaction(HttpServletRequest req, Float transactionAmount) {
    User user = currentUser.get().getUser();

    if (req.getParameter(bankAccountMessages.transactionType()).equals(bankAccountMessages.deposit())) {
      req.setAttribute(siteMap.contentPage(), siteMap.depositPage());
      accountBankDAO.deposit(transactionAmount, user.getUserID());
      return;
    }

    Float currentAmount = accountBankDAO.getCurrentUserBankAmount(user.getUserID());

    if (currentAmount > transactionAmount) {
      req.setAttribute(siteMap.contentPage(), siteMap.withdrawingPage());
      accountBankDAO.withdraw(transactionAmount, user.getUserID());
    }

  }
}
