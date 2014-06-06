package com.clouway.core;

import com.clouway.http.WithdrawingAccountServlet;
import com.google.inject.Provider;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by clouway on 5/29/14.
 */
public class WithdrawingAccountServletTest {

  private WithdrawingAccountServlet withdrawing;

  private CurrentUser currentUser;

//  Clock clock = new Clock() {
//    @Override
//    public String now() {
//      return CalendarUtil.may(2014, 5, 30).toString();
//    }
//  };

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private HttpServletRequest request;

  @Mock
  private HttpServletResponse response;

  @Mock
  private BankAccountMessages bankAccountMessages;

  @Mock
  private Provider<CurrentUser> currentUserProvider;

  @Mock
  private AccountBankDAO accountBankDAO;

  @Before
  public void setUp() {

    context.checking(new Expectations() {{
      oneOf(accountBankDAO).deposit(100, 1);

      oneOf(bankAccountMessages).withdrawingAmount();
      will(returnValue("withdrawingAmount"));
    }
    });

    User user = new User("emil", "emil", 1);

    currentUser = new CurrentUser(user);

    accountBankDAO.deposit(100, 1);

    withdrawing = new WithdrawingAccountServlet(accountBankDAO, bankAccountMessages, currentUserProvider);
  }

  @After
  public void after() throws ServletException, IOException {
    withdrawing.doPost(request, response);
  }

  @Test
  public void whenWithdrawingSameValueThanCurrentAmountDecrementWithThisValue() throws Exception {

    context.checking(new Expectations() {{

      oneOf(request).getParameter("withdrawingAmount");
      will(returnValue("30"));

      oneOf(currentUserProvider).get();
      will(returnValue(currentUser));

      oneOf(accountBankDAO).withdrawing(30, 1);

      oneOf(bankAccountMessages).mainPage();
      will(returnValue("mainPage.jsp"));

      oneOf(response).sendRedirect("mainPage.jsp");
    }
    });

  }

  @Test
  public void whenTryingWithdrawingInvalidValueThanTransactionIsFailed() throws Exception {

    context.checking(new Expectations() {{

      oneOf(request).getParameter("withdrawingAmount");
      will(returnValue("30LX"));

      oneOf(bankAccountMessages).error();
      will(returnValue("Transaction is failed"));

      oneOf(request).setAttribute("error", "Transaction is failed");

      oneOf(bankAccountMessages).withdrawingPage();
      will(returnValue("withdrawingPage.jsp"));

      oneOf(response).sendRedirect("withdrawingPage.jsp");
    }});

  }

  @Test
  public void whenWithdrawingValueIsLargerThenTheCurrentAmountThanTransactionIsFailed() throws Exception {
    context.checking(new Expectations() {{

      oneOf(request).getParameter("withdrawingAmount");
      will(returnValue("150"));

      oneOf(currentUserProvider).get();
      will(returnValue(currentUser));

      oneOf(accountBankDAO).withdrawing(150, 1);
      will(returnValue(-1));

      oneOf(bankAccountMessages).error();
      will(returnValue("Transaction is failed"));

      oneOf(request).setAttribute("error", "Transaction is failed");

      oneOf(bankAccountMessages).withdrawingPage();
      will(returnValue("withdrawingPage.jsp"));

      oneOf(response).sendRedirect("withdrawingPage.jsp");
    }
    });

  }
}
