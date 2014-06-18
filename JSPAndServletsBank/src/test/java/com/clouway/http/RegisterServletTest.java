package com.clouway.http;

import com.clouway.core.AuthenticateService;
import com.clouway.core.SiteMap;
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
 * Created by clouway on 6/13/14.
 */
public class RegisterServletTest {

  private RegisterServlet  registerServlet;

  private SessionID sessionID = new SessionID("");

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private HttpServletRequest request = null;

  @Mock
  private HttpServletResponse response = null;

  @Mock
  private SiteMap siteMap = null;

  @Mock
  private UserMessages userMessages = null;

  @Mock
  private AuthenticateService authenticateService = null;

  @Mock
  private UserDAO userDAO = null;

  @Before
  public void setUp() {
    registerServlet = new RegisterServlet(userDAO, siteMap, userMessages, authenticateService);
  }

  @Test
  public void registeredNewUser() throws Exception {

    context.checking(new Expectations() {{
      oneOf(userMessages).userName();
      will(returnValue("user_name"));

      oneOf(request).getParameter("user_name");
      will(returnValue("emil"));

      oneOf(userMessages).userPassword();
      will(returnValue("user_password"));

      oneOf(request).getParameter("user_password");
      will(returnValue("emil"));

      oneOf(authenticateService).authenticate("emil", "emil");
      will(returnValue(null));

      oneOf(userDAO).register("emil", "emil");

      oneOf(siteMap).mainPage();
      will(returnValue("mainPage.jsp"));

      oneOf(response).sendRedirect("mainPage.jsp");
    }
    });

    registerServlet.doPost(request, response);

  }

  @Test
  public void registeredUserWithEmployedUserName() throws Exception {
    context.checking(new Expectations() {{
      oneOf(userMessages).userName();
      will(returnValue("user_name"));

      oneOf(request).getParameter("user_name");
      will(returnValue("emil"));

      oneOf(userMessages).userPassword();
      will(returnValue("user_password"));

      oneOf(request).getParameter("user_password");
      will(returnValue("emil"));

      oneOf(authenticateService).authenticate("emil", "emil");
      will(returnValue(sessionID));

      oneOf(siteMap).loginPage();
      will(returnValue("loginPage.jsp"));

      oneOf(response).sendRedirect("loginPage.jsp");
    }
    });

    registerServlet.doPost(request, response);

  }
}
