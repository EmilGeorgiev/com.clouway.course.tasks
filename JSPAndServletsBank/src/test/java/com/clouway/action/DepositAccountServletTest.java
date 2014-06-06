package com.clouway.action;

import com.clouway.action.calendarutils.CalendarUtil;
import com.clouway.action.memory.InMemoryBankDAO;
import com.clouway.constants.BankAccountMessages;
import com.clouway.objects.Clock;
import com.clouway.objects.DepositAccountDAO;
import com.clouway.objects.User;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.google.inject.Provider;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by clouway on 5/28/14.
 */
public class DepositAccountServletTest {

  private DepositAccountServlet depositAccountServlet;
  private InMemoryBankDAO inMemoryBankDAO;
  private User user;
  private Cookie[] cookie;

  Clock clock = new Clock() {
    @Override
    public String now() {
      return CalendarUtil.may(2014, 5, 30).toString();
    }
  };

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private HttpServletRequest request;

  @Mock
  private BankAccountMessages bankAccountMessages;

  @Mock
  private HttpServletResponse response;

  @Mock
  private UserSessionsRepository userSessionsRepository;

  @Mock
  private DepositAccountDAO depositAccountDAO;

  @Mock
  private Provider<CurrentUser> currentUserProvider;

  @Before
  public void setUp() {

    cookie = new Cookie[]{new Cookie("UUID", "XC35LS")};
    user = new User("emil", "emil", 1);
    inMemoryBankDAO = new InMemoryBankDAO(clock, userSessionsRepository);
    depositAccountServlet = new DepositAccountServlet(inMemoryBankDAO, bankAccountMessages, currentUserProvider);
  }

  @Test
  public void whenDepositSomeValueThenCurrentAmountIncreaseWithThisValue() throws Exception {

    context.checking(new Expectations() {{
      oneOf(bankAccountMessages).depositAmount();
      will(returnValue("depositAmount"));

      oneOf(request).getParameter("depositAmount");
      will(returnValue("100"));

      oneOf(currentUserProvider).get();

      oneOf(bankAccountMessages).mainPage();
      will(returnValue("main.jsp"));

      oneOf(response).sendRedirect("main.jsp");
    }
    });

    depositAccountServlet.doPost(request, response);

  }


//  @Test
//  public void whenMakeDepositThenMakeAndTransaction() throws Exception {
//
//    context.checking(new Expectations() {{
//      oneOf(bankAccountMessages).depositAmount();
//      will(returnValue("depositAmount"));
//
//      oneOf(request).getParameter("depositAmount");
//      will(returnValue("200"));
//
//      oneOf(request).getCookies();
//      will(returnValue(cookie));
//
//      oneOf(bankAccountMessages).cookieName();
//      will(returnValue("UUID"));
//
//      oneOf(userSessionsRepository).findUserAssociatedWithSession("XC35LS");
//      will(returnValue(user));
//
//      oneOf(bankAccountMessages).mainPage();
//      will(returnValue("main.jsp"));
//
//      oneOf(response).sendRedirect("main.jsp");
//    }
//    });
//
//    depositAccountServlet.doPost(request, response);
//
//    inMemoryBankDAO.getAllTransactions();
//
//  }
}
