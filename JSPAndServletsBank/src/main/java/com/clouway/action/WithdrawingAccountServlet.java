package com.clouway.action;

import com.clouway.constants.BankAccountMessages;
import com.clouway.objects.AccountBankDAO;
import com.clouway.objects.User;
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

  @Inject
  public WithdrawingAccountServlet(AccountBankDAO accountBankDAO,
                                   BankAccountMessages bankAccountMessages,
                                   Provider<CurrentUser> currentUserProvider) {

    this.accountBankDAO = accountBankDAO;
    this.bankAccountMessages = bankAccountMessages;
    this.currentUserProvider = currentUserProvider;
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String withdrawing = req.getParameter(bankAccountMessages.withdrawingAmount());

    Integer drawingAmount;

    User user = currentUserProvider.get().get();

    try {

      drawingAmount = Integer.parseInt(withdrawing);

    } catch (NumberFormatException ex) {

      req.setAttribute("error", bankAccountMessages.error());

      resp.sendRedirect(bankAccountMessages.withdrawingPage());

      return;
    }

//    if (retrieveValue != 0) {
//
//      resp.sendRedirect(bankAccountMessages.mainPage());
//      return;
//    }

    accountBankDAO.withdrawing(drawingAmount, user.getUserID());

    resp.sendRedirect(bankAccountMessages.mainPage());

  }
}
