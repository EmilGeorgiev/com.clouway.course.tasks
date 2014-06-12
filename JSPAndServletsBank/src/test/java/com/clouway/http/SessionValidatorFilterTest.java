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

import javax.servlet.FilterChain;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by clouway on 6/11/14.
 */
public class SessionValidatorFilterTest {

  private SessionValidatorFilter validatorFilter;

  private CurrentUser currentUser;
  private Cookie[] cookies;

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private HttpServletResponse response;

  @Mock
  private HttpServletRequest request;

  @Mock
  private PageSiteMap pageSiteMap;
  @Mock
  private UserSessionsRepository userSessionsRepository;

  @Mock
  private FilterChain filterChain;

  @Before
  public void setUp() {
    User user = new User("emil", "emil", 1);
    currentUser = new CurrentUser(user);

    cookies = new Cookie[]{new Cookie("sessionID", "XCN67890")};

    validatorFilter = new SessionValidatorFilter(pageSiteMap, userSessionsRepository, Providers.of(currentUser));
  }

  @Test
  public void whenUserSessionDoesExistThanRequestIsAllowed() throws Exception {

    context.checking(new Expectations() {{
      oneOf(request).getCookies();
      will(returnValue(cookies));

      oneOf(userSessionsRepository).isUserExistBySession(cookies[1].getValue());
      will(returnValue(true));

    }
    });

    validatorFilter.doFilter(request, response, filterChain);

  }
}
