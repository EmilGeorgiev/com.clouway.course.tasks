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
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by clouway on 5/28/14.
 */
public class DepositAccountServletTest {

  private DepositAccountServlet depositAccountServlet;

  private CurrentUser currentUser = null;

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private HttpServletRequest request;

  @Mock
  private HttpServletResponse response;

  @Mock
  private BankAccountMessages bankAccountMessages;

  @Mock
  private PageMessages pageMessages;

  @Mock
  private AccountBankDAO accountBankDAO;



  @Before
  public void setUp() {

    User user = new User("emil", "emil", 1);

    currentUser = new CurrentUser(user);

    depositAccountServlet = new DepositAccountServlet(accountBankDAO,
                                          bankAccountMessages,
                                          Providers.of(currentUser),
                                          pageMessages);
  }

  @Test
  public void depositToAccount() throws Exception {

    context.checking(new Expectations() {{

      oneOf(bankAccountMessages).depositAmount();
      will(returnValue("depositAmount"));

      oneOf(request).getParameter("depositAmount");
      will(returnValue("100"));

      oneOf(accountBankDAO).deposit(100, 1);

      oneOf(pageMessages).mainPage();
      will(returnValue("mainPage.jsp"));

      oneOf(response).sendRedirect("mainPage.jsp");

    }
    });

    depositAccountServlet.doPost(request, response);

  }

  @Test
  public void whenTryingDepositInvalidValueThanTransactionIsFailed() throws Exception {

    context.checking(new Expectations() {{

      oneOf(bankAccountMessages).depositAmount();
      will(returnValue("depositAmount"));

      oneOf(request).getParameter("depositAmount");
      will(returnValue("12XV5"));

      oneOf(pageMessages).login();
      will(returnValue("loginPage.jsp"));

      oneOf(response).sendRedirect("loginPage.jsp");

    }
    });

    depositAccountServlet.doPost(request, response);
  }
}
