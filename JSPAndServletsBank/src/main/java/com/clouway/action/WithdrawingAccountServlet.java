package com.clouway.action;

import com.clouway.constants.BankAccountMessages;
import com.clouway.objects.DepositAccountDAO;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by clouway on 5/29/14.
 */
@Singleton
public class WithdrawingAccountServlet extends HttpServlet{

  private final DepositAccountDAO depositAccountDAO;
  private final BankAccountMessages bankAccountMessages;

  @Inject
  public WithdrawingAccountServlet(DepositAccountDAO depositAccountDAO, BankAccountMessages bankAccountMessages) {

    this.depositAccountDAO = depositAccountDAO;
    this.bankAccountMessages = bankAccountMessages;
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String withdrawing = req.getParameter(bankAccountMessages.withdrawingAmount());

    Integer drawingAmount = Integer.parseInt(withdrawing);

    Cookie[] cookies = req.getCookies();

    String UUID = null;

    for (Cookie cookie : cookies) {
      if (cookie.getName().equals(bankAccountMessages.cookieName())) {
        UUID = cookie.getValue();
      }
    }

    int retrieveValue = depositAccountDAO.withdrawing(drawingAmount, UUID);

    if (retrieveValue != 0) {

      resp.sendRedirect(bankAccountMessages.mainPage());
      return;
    }

    resp.sendRedirect(bankAccountMessages.withdrawingPage());

  }
}
