package com.clouway.http;

import com.clouway.core.SiteMap;
import com.clouway.core.UserSessionsRepository;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LogoutServletTest {

  private LogoutServlet logoutServlet;

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private HttpServletRequest servletRequest = null;

  @Mock
  private HttpServletResponse servletResponse = null;

  @Mock
  private UserSessionsRepository userSessionsRepository = null;

  @Mock
  private SiteMap siteMap = null;

  @Before
  public void setUp() {

    logoutServlet = new LogoutServlet(userSessionsRepository, siteMap);
  }

  @Test
  public void userSessionIsExpired() throws Exception {

    context.checking(new Expectations() {{

      oneOf(servletRequest).getCookies();
      will(returnValue(with(any(Cookie[].class))));

      oneOf(siteMap).loginPage();
      will(returnValue("loginPage.jsp"));

      oneOf(servletResponse).sendRedirect("loginPage.jsp");
    }
    });

    logoutServlet.doPost(servletRequest, servletResponse);

  }

}
