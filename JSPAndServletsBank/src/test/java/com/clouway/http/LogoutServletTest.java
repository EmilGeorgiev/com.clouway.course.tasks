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

/**
 * Created by clouway on 6/11/14.
 */
public class LogoutServletTest {

  private LogoutServlet logoutServlet;
  private CurrentUser currentUser;
  private User user = new User("emil", "emil", 1);

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private HttpServletRequest servletRequest;

  @Mock
  private HttpServletResponse servletResponse;

  @Mock
  private UserSessionsRepository userSessionsRepository;

  @Mock
  private PageSiteMap pageSiteMap;

  @Before
  public void setUp() {


    currentUser = new CurrentUser(user);

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
