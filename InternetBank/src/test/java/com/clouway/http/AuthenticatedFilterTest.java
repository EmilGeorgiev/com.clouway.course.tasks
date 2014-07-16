package com.clouway.http;

import com.clouway.core.SessionRepository;
import com.clouway.core.SiteMap;
import com.clouway.core.User;
import com.google.inject.util.Providers;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by clouway on 7/16/14.
 */
public class AuthenticatedFilterTest {

  private AuthenticatedFilter authenticatedFilter;
  private User user;

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private SessionRepository sessionRepository = null;

  @Mock
  private HttpServletResponse response = null;

  @Mock
  private HttpServletRequest request = null;

  @Mock
  private FilterChain filterChain = null;

  @Mock
  private SiteMap siteMap = null;

  @Before
  public void setUp() {

    user = new User(name("test"), password("testPass"), userId(24), sessionID("45XQ"));

    authenticatedFilter = new AuthenticatedFilter(sessionRepository, Providers.of(user), siteMap);
  }

  private String sessionID(String sessionID) {
    return sessionID;
  }

  private Object userId(int userId) {
    return userId;
  }

  private String password(String password) {
    return password;
  }

  private String name(String name) {
    return name;
  }

  @Test
  public void authenticateUserWithValidSession() throws Exception {

    context.checking(new Expectations() {{

      oneOf(sessionRepository).authenticateSession(user.getUserSession());
      will(returnValue(true));

      oneOf(filterChain).doFilter(request, response);

    }
    });

    authenticatedFilter.doFilter(request, response, filterChain);

  }

  @Test
  public void authenticateUserWithExpireSession() throws Exception {

    context.checking(new Expectations() {{

      oneOf(sessionRepository).authenticateSession(user.getUserSession());
      will(returnValue(false));

      oneOf(siteMap).loginPage();
      will(returnValue("LoginController.html"));

      oneOf(response).sendRedirect("LoginController.html");

      oneOf(filterChain).doFilter(request, response);

    }
    });

    authenticatedFilter.doFilter(request, response, filterChain);
  }
}
