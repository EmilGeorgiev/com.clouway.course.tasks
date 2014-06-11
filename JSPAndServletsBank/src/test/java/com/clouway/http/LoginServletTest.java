package com.clouway.http;

import com.clouway.core.BankAccountMessages;
import com.clouway.core.PageMessages;
import com.clouway.core.UserDAO;
import com.clouway.core.UserMessages;
import com.clouway.core.UserSessionsRepository;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by clouway on 5/30/14.
 */
public class LoginServletTest {

  private LoginServlet loginServlet;

  //private InMemoryUserDAO inMemoryUserDAO;

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private BankAccountMessages bankAccountMessages;

  @Mock
  private HttpServletResponse response;

  @Mock
  private HttpServletRequest request;

  @Mock
  private UserMessages userMessages;

  @Mock
  private PageMessages pageMessages;

  @Mock
  private UserDAO userDAO;

  @Mock
  private UserSessionsRepository userSessionsRepository;

  @Before
  public void setUp() {

    context.checking(new Expectations() {{
      oneOf(userMessages).userName();
      will(returnValue("user_name"));

      oneOf(request).getParameter("user_name");
      will(returnValue("emil"));

      oneOf(userMessages).userPassword();
      will(returnValue("user_password"));

      oneOf(request).getParameter("user_password");
      will(returnValue("emil"));
    }
    });

    loginServlet = new LoginServlet(userDAO,
                                    userMessages,
                                    pageMessages,
                                    userSessionsRepository);
  }

  @Test
  public void whenUserWhoExistIsLoggedThenLoginIsSuccess() throws Exception {
    context.checking(new Expectations() {{

      oneOf(userDAO).isUserExist("emil", "emil");
      will(returnValue(true));

      oneOf(pageMessages).mainPage();
      will(returnValue("mainPage.jsp"));

      oneOf(response).sendRedirect("mainPage.jsp");
    }
    });

    loginServlet.doPost(request, response);
  }

  @Test
  public void whenUserWhoDoesNotExistIsTryingToLoggedThenLoginIsFailed() throws Exception {

    context.checking(new Expectations() {{

      oneOf(userDAO).isUserExist("emil", "emil");
      will(returnValue(false));

      oneOf(pageMessages).loginPage();
      will(returnValue("loginPage.jsp"));

      oneOf(response).sendRedirect("loginPage.jsp");
    }
    });

    loginServlet.doPost(request, response);

  }
}
