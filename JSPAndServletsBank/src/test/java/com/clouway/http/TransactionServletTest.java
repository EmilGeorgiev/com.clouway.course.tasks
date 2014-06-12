package com.clouway.http;

import com.clouway.core.AccountBankDAO;
import com.clouway.core.BankAccountMessages;
import com.clouway.core.CurrentAmountRepository;
import com.clouway.core.CurrentUser;
import com.clouway.core.PageSiteMap;
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
 * Created by clouway on 6/11/14.
 */
public class TransactionServletTest {

  private TransactionServlet transactionServlet;

  private CurrentUser currentUser;

  private User user = new User("emil", "emil", 1);

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private HttpServletRequest servletRequest;

  @Mock
  private HttpServletResponse servletResponse;

  @Mock
  private BankAccountMessages bankAccountMessages;

  @Mock
  private PageSiteMap pageSiteMap;

  @Mock
  private AccountBankDAO accountBankDAO;

  @Mock
  private CurrentAmountRepository currentAmountRepository;


  @Before
  public void setUp() {


    context.checking(new Expectations() {{
      oneOf(accountBankDAO).deposit(100, 1);
    }
    });


    accountBankDAO.deposit(100, 1);


    currentUser = new CurrentUser(user);

    transactionServlet = new TransactionServlet(accountBankDAO,
                                                bankAccountMessages,
            pageSiteMap,
                                                Providers.of(currentUser));

  }

//  @Test
//  public void whenDepositSomeValueThenCurrentValueIncrementWithThisValue() throws Exception {
//
//    context.checking(new Expectations() {{
//
//      oneOf(bankAccountMessages).transactionAmount();
//      will(returnValue("transactionAmount"));
//
//      oneOf(servletRequest).getParameter("transactionAmount");
//      will(returnValue("50"));
//
//      oneOf(pageMessages).depositPage();
//      will(returnValue("depositPage.jsp"));
//
//      oneOf(servletRequest).getHeader("Referer");
//      will(returnValue("depositPage.jsp"));
//
//      oneOf(accountBankDAO).deposit(50, 1);
//
//      oneOf(pageMessages).mainPage();
//      will(returnValue("mainPage.jsp"));
//
//      oneOf(servletResponse).sendRedirect("mainPage.jsp");
//
//    }
//    });
//
//    transactionServlet.doPost(servletRequest, servletResponse);
//
//  }

  @Test
  public void whenTryingTransferInvalidValueThenTransactionIsFailed() throws Exception {

    context.checking(new Expectations() {{
      oneOf(bankAccountMessages).transactionAmount();
      will(returnValue("transactionAmount"));

      oneOf(servletRequest).getParameter("transactionAmount");
      will(returnValue("50XL"));

      oneOf(pageSiteMap).mainPage();
      will(returnValue("mainPage.jsp"));

      oneOf(servletResponse).sendRedirect("mainPage.jsp");
    }
    });

    transactionServlet.doPost(servletRequest, servletResponse);

  }

  @Test
  public void withdrawingAmount() throws Exception {

    context.checking(new Expectations() {{
      oneOf(bankAccountMessages).transactionAmount();
      will(returnValue("transactionAmount"));

      oneOf(servletRequest).getParameter("transactionAmount");
      will(returnValue("50"));

      oneOf(bankAccountMessages).transaction();
      will(returnValue("transaction"));

      oneOf(servletRequest).getParameter("transaction");
      will(returnValue("withdrawingPage.jsp"));

      oneOf(bankAccountMessages).deposit();
      will(returnValue("deposit"));

      oneOf(accountBankDAO).withdrawing(50, user.getUserID());

      oneOf(pageSiteMap).mainPage();
      will(returnValue("mainPage.jsp"));

      oneOf(servletResponse).sendRedirect("mainPage.jsp");
    }
    });

    transactionServlet.doPost(servletRequest, servletResponse);

  }
}
