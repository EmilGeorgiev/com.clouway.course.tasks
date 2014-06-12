package com.clouway.http;

import com.clouway.core.BankAccountMessages;
import com.clouway.core.PageSiteMap;
import com.clouway.core.SessionID;
import com.clouway.core.UserDAO;
import com.clouway.core.UserMessages;
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
  private SessionID sessionID;

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
  private PageSiteMap pageSiteMap;

  @Mock
  private UserDAO userDAO;


  @Before
  public void setUp() {

    loginServlet = new LoginServlet(userDAO, userMessages, pageSiteMap);

    sessionID = new SessionID("XL4562GD");

  }

  @Test
  public void loginWithExistingUser() throws Exception {
    context.checking(new Expectations() {{

      expectAssertion();

      oneOf(userDAO).authenticate("emil", "emil");
      will(returnValue(sessionID));

      oneOf(response).addCookie(sessionID.getCookie());

      oneOf(pageSiteMap).mainPage();
      will(returnValue("mainPage.jsp"));

      oneOf(response).sendRedirect("mainPage.jsp");
    }
    });

    loginServlet.doPost(request, response);
  }



  @Test
  public void loginWithNotExistingUser() throws Exception {

    context.checking(new Expectations() {{

      expectAssertion();

      oneOf(userDAO).authenticate("emil", "emil");
      will(returnValue(null));

      oneOf(pageSiteMap).loginPage();
      will(returnValue("loginPage.jsp"));

      oneOf(response).sendRedirect("loginPage.jsp");
    }
    });

    loginServlet.doPost(request, response);

  }

  private void expectAssertion() {
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
  }
}
