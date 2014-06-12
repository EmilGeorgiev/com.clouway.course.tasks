package com.clouway.http.down;

import com.clouway.core.BankAccountMessages;
import com.clouway.core.CurrentUser;
import com.clouway.core.AccountBankDAO;
import com.clouway.core.PageSiteMap;
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
 * Created by clouway on 5/29/14.
 */
@Singleton
public class WithdrawingAccountServlet extends HttpServlet{

  private final AccountBankDAO accountBankDAO;

  private final BankAccountMessages bankAccountMessages;
  private final Provider<CurrentUser> currentUserProvider;
  private final PageSiteMap pageSiteMapProvider;

  @Inject
  public WithdrawingAccountServlet(AccountBankDAO accountBankDAO,
                                   BankAccountMessages bankAccountMessages,
                                   Provider<CurrentUser> currentUserProvider,
                                   PageSiteMap pageSiteMapProvider) {

    this.accountBankDAO = accountBankDAO;
    this.bankAccountMessages = bankAccountMessages;
    this.currentUserProvider = currentUserProvider;
    this.pageSiteMapProvider = pageSiteMapProvider;
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String withdrawing = req.getParameter(bankAccountMessages.withdrawingAmount());

    Integer drawingAmount;

    try {

      drawingAmount = Integer.parseInt(withdrawing);

    } catch (NumberFormatException ex) {

      resp.sendRedirect(pageSiteMapProvider.loginPage());

      return;
    }

    User user = currentUserProvider.get().getUser();

    int retrievedAmount = accountBankDAO.withdrawing(drawingAmount, user.getUserID());

    if (retrievedAmount == -1) {

      resp.sendRedirect(pageSiteMapProvider.withdrawingPage());
      return;
    }

    resp.sendRedirect(pageSiteMapProvider.mainPage());

  }
}
