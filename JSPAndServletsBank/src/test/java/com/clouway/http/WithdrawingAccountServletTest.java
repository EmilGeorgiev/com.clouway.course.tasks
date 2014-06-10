package com.clouway.http;

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
  private BankAccountMessages bankAccountMessages;
  private PageMessages pageMessages;

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private HttpServletRequest request;

  @Mock
  private HttpServletResponse response;

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

    withdrawing = new WithdrawingAccountServlet(accountBankDAO,
            Providers.of(bankAccountMessages),
            Providers.of(currentUser),
            Providers.of(pageMessages));
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

      oneOf(accountBankDAO).withdrawing(30, 1);
      oneOf(response).sendRedirect("mainPage.jsp");
    }
    });

  }

  @Test
  public void whenTryingWithdrawingInvalidValueThanTransactionIsFailed() throws Exception {

    context.checking(new Expectations() {{

      oneOf(request).getParameter("withdrawingAmount");
      will(returnValue("30LX"));

      oneOf(request).setAttribute("error", "Transaction is failed");

      oneOf(response).sendRedirect("withdrawingPage.jsp");
    }});

  }

  @Test
  public void whenWithdrawingValueIsLargerThenTheCurrentAmountThanTransactionIsFailed() throws Exception {
    context.checking(new Expectations() {{

      oneOf(request).getParameter("withdrawingAmount");
      will(returnValue("150"));

      oneOf(accountBankDAO).withdrawing(150, 1);
      will(returnValue(-1));

      oneOf(response).sendRedirect("withdrawingPage.jsp");
    }
    });

  }
}
