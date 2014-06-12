package com.clouway.http;

import com.clouway.core.CurrentUser;
import com.clouway.core.PageSiteMap;
import com.clouway.core.User;
import com.clouway.core.UserSessionsRepository;
import com.google.inject.util.Providers;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LogoutServletTest {

  private LogoutServlet logoutServlet;
  private User user = new User("emil", "emil", 1, "SSJ765");

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private HttpServletRequest servletRequest = null;

  @Mock
  private HttpServletResponse servletResponse = null;

  @Mock
  private UserSessionsRepository userSessionsRepository = null;

  @Mock
  private PageSiteMap pageSiteMap = null;

  @Before
  public void setUp() {


    CurrentUser currentUser = new CurrentUser(user);

    logoutServlet = new LogoutServlet(userSessionsRepository, pageSiteMap, Providers.of(currentUser));
  }

  @Test
  public void userIsLogout() throws Exception {

    context.checking(new Expectations() {{

      oneOf(userSessionsRepository).deleteSession(user.getUserID());

      oneOf(pageSiteMap).loginPage();
      will(returnValue("loginPage.jsp"));

      oneOf(servletResponse).sendRedirect("loginPage.jsp");
    }
    });

    logoutServlet.doPost(servletRequest, servletResponse);

  }
}
