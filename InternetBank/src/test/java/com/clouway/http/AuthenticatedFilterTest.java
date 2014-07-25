package com.clouway.http;

import com.clouway.core.SessionRepository;
import com.clouway.core.SiteMap;
import com.clouway.core.User;
import com.clouway.util.CalendarUtil;
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
  private CalendarUtil clock;


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

    clock = new CalendarUtil(2014, 7, 23, 12, 45, 34);

    user = new User(userId("24"), sessionID("45XQ"));

    authenticatedFilter = new AuthenticatedFilter(sessionRepository,
                                                  Providers.of(user),
                                                  siteMap,
                                                  clock);
  }

  @Test
  public void authenticateUserWithValidSession() throws Exception {

    context.checking(new Expectations() {{

      oneOf(sessionRepository).authenticateSession(user.getUserSession(), clock);
      will(returnValue(user));

      oneOf(filterChain).doFilter(request, response);

    }
    });

    authenticatedFilter.doFilter(request, response, filterChain);

  }

  @Test
  public void authenticateUserWithExpireSession() throws Exception {

    context.checking(new Expectations() {{

      oneOf(sessionRepository).authenticateSession(user.getUserSession(), clock);
      will(returnValue(null));

      oneOf(siteMap).loginController();
      will(returnValue("/loginController"));

      oneOf(response).sendRedirect("/loginController");

      oneOf(filterChain).doFilter(request, response);

    }
    });

    authenticatedFilter.doFilter(request, response, filterChain);
  }

  private String sessionID(String sessionID) {
    return sessionID;
  }

  private String userId(String userId) {
    return userId;
  }

}
