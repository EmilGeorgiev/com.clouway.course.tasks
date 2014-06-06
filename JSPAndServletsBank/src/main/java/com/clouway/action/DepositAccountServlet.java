package com.clouway.action;

import com.clouway.constants.BankAccountMessages;
import com.clouway.objects.DepositAccountDAO;
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
 * Created by clouway on 5/28/14.
 */
@Singleton
public class DepositAccountServlet extends HttpServlet{

  private final DepositAccountDAO depositAccountDAO;
  private final BankAccountMessages bankAccountMessages;
  private final Provider<CurrentUser> currentUserProvider;

  @Inject
  public DepositAccountServlet(DepositAccountDAO depositAccountDAO,
                               BankAccountMessages bankAccountMessages,
                               Provider<CurrentUser> currentUserProvider) {
    this.depositAccountDAO = depositAccountDAO;
    this.bankAccountMessages = bankAccountMessages;
    this.currentUserProvider = currentUserProvider;
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    //Get constant for deposit and than get parameter from request.
    String amount = req.getParameter(bankAccountMessages.depositAmount());

    Integer depositAmount = Integer.parseInt(amount);


    User user = currentUser

    //Deposit some value in account of user with session <parameter>uuid<parameter>
    depositAccountDAO.deposit(depositAmount, uuid);

    //Return back to main page.
    resp.sendRedirect(bankAccountMessages.mainPage());
  }
}
