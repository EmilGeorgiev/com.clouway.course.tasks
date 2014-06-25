package com.clouway.http;

import com.clouway.core.AccountBankDAO;
import com.clouway.core.BankAccountMessages;
import com.clouway.core.CurrentUser;
import com.clouway.core.SiteMap;
import com.clouway.core.User;
import com.clouway.persistents.util.BankUtil;
import com.google.inject.util.Providers;
import org.hamcrest.core.IsNull;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by clouway on 6/11/14.
 */
public class TransactionServletTest {

  private TransactionServlet transactionServlet;

  private User user = new User("emil", "emil", 1);

  private AccountBankDAO accountBankDAO;

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private HttpServletRequest servletRequest = null;

  @Mock
  private HttpServletResponse servletResponse = null;

  @Mock
  private BankAccountMessages bankAccountMessages = null;

  @Mock
  private SiteMap siteMap = null;


  @Before
  public void setUp() {

    accountBankDAO = new BankUtil();

    CurrentUser currentUser = new CurrentUser(user);

    transactionServlet = new TransactionServlet(accountBankDAO,
                                                bankAccountMessages,
                                                siteMap,
                                                Providers.of(currentUser));

  }

  @Test
  public void whenTryingTransferInvalidValueThenTransactionIsFailed() throws Exception {

    pretendThatUserHasDepositOf("50RX", userID(5));

    context.checking(new Expectations() {{
      oneOf(bankAccountMessages).transactionAmount();
      will(returnValue("transactionAmount"));

      oneOf(servletRequest).getParameter("transactionAmount");
      will(returnValue("50RX"));

      oneOf(siteMap).mainPage();
      will(returnValue("mainPage.jsp"));

      oneOf(servletResponse).sendRedirect("mainPage.jsp");
    }
    });

    transactionServlet.doPost(servletRequest, servletResponse);

    Float currentAmount = accountBankDAO.getCurrentUserBankAmount(userID(5));

    assertThat(currentAmount, new IsNull());

  }

  @Test
  public void withdrawingAmount() throws Exception {

    pretendThatUserHasDepositOf("100", userID(4));

    context.checking(new Expectations() {{

      oneOf(bankAccountMessages).transactionAmount();
      will(returnValue("transactionAmount"));

      oneOf(servletRequest).getParameter("transactionAmount");
      will(returnValue("40"));

      oneOf(bankAccountMessages).transactionType();
      will(returnValue("transactionType"));

      oneOf(servletRequest).getParameter("transactionType");
      will(returnValue("withdraw"));

      oneOf(bankAccountMessages).deposit();
      will(returnValue("deposit"));

      oneOf(siteMap).contentPage();
      will(returnValue("withdrawPage"));

      oneOf(siteMap).withdrawingPage();
      will(returnValue("withdrawPage.jsp"));

      oneOf(servletRequest).setAttribute("withdrawPage", "withdrawPage.jsp");

      oneOf(siteMap).mainPage();
      will(returnValue("mainPage.jsp"));

      oneOf(servletRequest).getRequestDispatcher("mainPage.jsp");

    }
    });

    transactionServlet.doPost(servletRequest, servletResponse);

    Float currentAmount = accountBankDAO.getCurrentUserBankAmount(userID(4));

    assertThat(currentAmount, is(60f));

  }

  @Test
  public void whenTryingWithdrawLargeValueThenTransactionIsFailed() throws Exception {
    pretendThatUserHasDepositOf("50", userID(7));

    pretendThatUserWithdrawOf("100", userID(7));

    context.checking(new Expectations() {
      {
        oneOf(bankAccountMessages).transactionAmount();
        will(returnValue("transactionAmount"));

        oneOf(servletRequest).getParameter("transactionAmount");
        will(returnValue("100"));

        oneOf(bankAccountMessages).transactionType();
        will(returnValue("transactionType"));

        oneOf(servletRequest).getParameter("transactionType");
        will(returnValue("withdraw"));

        oneOf(bankAccountMessages).deposit();
        will(returnValue("deposit"));

        oneOf(siteMap).mainPage();
        will(returnValue("mainPage.jsp"));

        oneOf(servletRequest).getRequestDispatcher("mainPage.jsp");

      }
    });

    transactionServlet.doPost(servletRequest, servletResponse);

    float currentAmount = accountBankDAO.getCurrentUserBankAmount(userID(7));

    assertThat(currentAmount, is(50f));

  }

  private void pretendThatUserWithdrawOf(String withdrawAmount, int userID) {
    Float amount;

    try {
      amount = Float.valueOf(withdrawAmount);
    } catch (NumberFormatException ex) {
      return;
    }

    if (accountBankDAO.getCurrentUserBankAmount(userID) > amount) {

      accountBankDAO.withdraw(amount, userID);
    }
  }

  private void pretendThatUserHasDepositOf(String depositAmount, int userID) {

    Float amount;

    try {
      amount = Float.valueOf(depositAmount);
    } catch (NumberFormatException ex) {
      return;
    }
    accountBankDAO.deposit(amount, userID);
  }

  private int userID(int userID) {
    user.setUserID(userID);
    return userID;
  }
}
