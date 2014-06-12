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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionValidatorFilterTest {

  private SessionValidatorFilter validatorFilter;

  private  User user = new User("emil", "emil", 1, "XLK3452");

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private HttpServletResponse response = null;

  @Mock
  private HttpServletRequest request = null;

  @Mock
  private PageSiteMap pageSiteMap = null;

  @Mock
  private UserSessionsRepository userSessionsRepository = null;

  @Mock
  private FilterChain filterChain = null;

  @Before
  public void setUp() {

    CurrentUser currentUser = new CurrentUser(user);

    validatorFilter = new SessionValidatorFilter(pageSiteMap, userSessionsRepository, Providers.of(currentUser));
  }

  @Test
  public void userRequestIsAllowed() throws Exception {

    context.checking(new Expectations() {{

      oneOf(userSessionsRepository).isValidUserSession(user.getSessionID().getSessionID());
      will(returnValue(true));

      oneOf(filterChain).doFilter(request, response);

    }
    });

    validatorFilter.doFilter(request, response, filterChain);

  }

//  @Test
//  public void userSessionIsExpiredAndHeRedirectToLoginPage() throws Exception {
//
//    context.checking(new Expectations() {{
//
//    }
//    });
//
//  }
}
