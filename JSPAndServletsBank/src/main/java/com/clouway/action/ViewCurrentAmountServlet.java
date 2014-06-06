package com.clouway.action;

import com.clouway.constants.BankAccountMessages;
import com.clouway.objects.AccountBankDAO;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import javax.servlet.http.HttpServlet;

/**
 * Created by clouway on 5/29/14.
 */
@Singleton
public class ViewCurrentAmountServlet extends HttpServlet{

  private final AccountBankDAO accountBankDAO;

  private final BankAccountMessages bankAccountMessages;

  @Inject
  public ViewCurrentAmountServlet(AccountBankDAO accountBankDAO, BankAccountMessages bankAccountMessages) {
    this.accountBankDAO = accountBankDAO;
    this.bankAccountMessages = bankAccountMessages;
  }

//  @Override
//  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//    int currentAmount = depositAccountDAO.getCurrentUserBankAmount();
//
//    StringBuilder messageBuilder = new StringBuilder();
//
//    messageBuilder.append(bankAccountMessages.currentAccountMessage() + currentAmount);
//
//    List<Transaction> transactions = depositAccountDAO.getHistory();
//
//    if (transactions != null) {
//      for (Transaction transaction : transactions) {
//        messageBuilder.append("\n" + transaction.toString());
//      }
//    }
//
//
//    resp.sendRedirect(Constants.MAIN_PAGE);
//  }
}
