package com.clouway.http;

import com.clouway.core.*;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Singleton
public class MainServlet extends HttpServlet {
  private final AccountBankDAO accountBankDAO;
  private final TransactionHistory transactionHistory;
  private final SiteMap siteMap;
  private final Provider<CurrentUser> currentUserProvider;
  private final BankAccountMessages bankAccountMessages;


  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doPost(req, resp);
  }

  @Inject
  public MainServlet(AccountBankDAO accountBankDAO,
                     TransactionHistory transactionHistory,
                     SiteMap siteMap,
                     Provider<CurrentUser> currentUserProvider,
                     BankAccountMessages bankAccountMessages) {

    this.accountBankDAO = accountBankDAO;
    this.transactionHistory = transactionHistory;
    this.siteMap = siteMap;

    this.currentUserProvider = currentUserProvider;
    this.bankAccountMessages = bankAccountMessages;
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    String contentPage = (String) req.getAttribute(siteMap.contentPage());

    if ((contentPage == null) || (contentPage.equals(siteMap.viewAmountPage()))) {

      req.setAttribute(siteMap.contentPage(), siteMap.viewAmountPage());

      User user = currentUserProvider.get().getUser();

      float amount = accountBankDAO.getCurrentUserBankAmount(user.getUserID());

      req.setAttribute(bankAccountMessages.currentAmount(), amount);

      List<Transaction> transactions = transactionHistory.getUserHistory(user.getUserID());

      req.setAttribute(bankAccountMessages.transactionHistory(), transactions);

    } else {

      req.setAttribute(siteMap.contentPage(), contentPage);
    }

    RequestDispatcher requestDispatcher = req.getRequestDispatcher(siteMap.mainPage());

    requestDispatcher.include(req, resp);
  }
}
