package com.clouway.http.down;

import com.clouway.core.AccountBankDAO;
import com.clouway.core.BankAccountMessages;
import com.clouway.core.CurrentUser;
import com.clouway.core.PageMessages;
import com.clouway.core.User;
import com.google.inject.util.Providers;
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

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private HttpServletRequest request;

  @Mock
  private HttpServletResponse response;

  @Mock
  private AccountBankDAO accountBankDAO;

  @Mock
  private BankAccountMessages bankAccountMessages;

  @Mock
  private PageMessages pageMessages;

  @Before
  public void setUp() {

    User user = new User("emil", "emil", 1);

    currentUser = new CurrentUser(user);

    withdrawing = new WithdrawingAccountServlet(accountBankDAO,
            bankAccountMessages,
            Providers.of(currentUser),
            pageMessages);
  }

  @After
  public void after() throws ServletException, IOException {
    withdrawing.doPost(request, response);
  }

  @Test
  public void whenWithdrawingSameValueThanCurrentAmountDecrementWithThisValue() throws Exception {

    context.checking(new Expectations() {{

      oneOf(bankAccountMessages).withdrawingAmount();
      will(returnValue("withdrawingAmount"));

      oneOf(request).getParameter("withdrawingAmount");
      will(returnValue("30"));

      oneOf(accountBankDAO).withdrawing(30, 1);

      oneOf(pageMessages).mainPage();
      will(returnValue("mainPage.jsp"));

      oneOf(response).sendRedirect("mainPage.jsp");
    }
    });

  }

  @Test
  public void whenTryingWithdrawingInvalidValueThanTransactionIsFailed() throws Exception {

    context.checking(new Expectations() {{

      oneOf(bankAccountMessages).withdrawingAmount();
      will(returnValue("withdrawingAmount"));

      oneOf(request).getParameter("withdrawingAmount");
      will(returnValue("30LX"));

      oneOf(pageMessages).loginPage();
      will(returnValue("loginPage.jsp"));

      oneOf(response).sendRedirect("loginPage.jsp");
    }});

  }

  @Test
  public void whenWithdrawingValueIsLargerThenTheCurrentAmountThanTransactionIsFailed() throws Exception {
    context.checking(new Expectations() {{

      oneOf(bankAccountMessages).withdrawingAmount();
      will(returnValue("withdrawingAmount"));

      oneOf(request).getParameter("withdrawingAmount");
      will(returnValue("150"));

      oneOf(accountBankDAO).withdrawing(150, 1);
      will(returnValue(-1));


      oneOf(pageMessages).withdrawingPage();
      will(returnValue("withdrawingPage.jsp"));

      oneOf(response).sendRedirect("withdrawingPage.jsp");
    }
    });

  }
}
