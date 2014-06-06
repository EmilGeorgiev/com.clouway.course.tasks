package com.clouway.action;

import com.clouway.constants.BankAccountMessages;
import com.clouway.objects.DepositAccountDAO;
import com.clouway.objects.User;
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
 * Created by clouway on 5/28/14.
 */
public class DepositAccountServletTest {

  private DepositAccountServlet depositAccountServlet;

  private CurrentUser currentUser;

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private HttpServletRequest request;

  @Mock
  private BankAccountMessages bankAccountMessages;

  @Mock
  private HttpServletResponse response;

  @Mock
  private DepositAccountDAO depositAccountDAO;

  @Mock
  private Provider<CurrentUser> currentUserProvider;

  @Before
  public void setUp() {

    context.checking(new Expectations() {{
      oneOf(bankAccountMessages).depositAmount();
      will(returnValue("depositAmount"));
    }
    });

    User user = new User("emil", "emil", 1);

    currentUser = new CurrentUser(user);

    depositAccountServlet = new DepositAccountServlet(depositAccountDAO, bankAccountMessages, currentUserProvider);
  }

  @After
  public void after() throws ServletException, IOException {
    depositAccountServlet.doPost(request, response);
  }

  @Test
  public void whenDepositSomeValueThenCurrentAmountIncreaseWithThisValue() throws Exception {

    context.checking(new Expectations() {{

      oneOf(request).getParameter("depositAmount");
      will(returnValue("100"));

      oneOf(currentUserProvider).get();
      will(returnValue(currentUser));

      oneOf(depositAccountDAO).deposit(100, 1);

      oneOf(bankAccountMessages).mainPage();
      will(returnValue("main.jsp"));

      oneOf(response).sendRedirect("main.jsp");
    }
    });

  }

  @Test
  public void whenTryingDepositInvalidValueThanTransactionIsFailed() throws Exception {

    context.checking(new Expectations() {{

      oneOf(request).getParameter("depositAmount");
      will(returnValue("12XV5"));

      oneOf(bankAccountMessages).error();
      will(returnValue("Invalid value"));

      oneOf(request).setAttribute("error", "Invalid value");

      oneOf(bankAccountMessages).login();
      will(returnValue("loginPage.jsp"));

      oneOf(response).sendRedirect("loginPage.jsp");

    }
    });
  }
}
