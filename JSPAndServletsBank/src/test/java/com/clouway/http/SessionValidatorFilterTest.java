package com.clouway.http;

import com.clouway.core.Clock;
import com.clouway.core.SiteMap;
import com.clouway.core.UserSessionsRepository;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import static com.clouway.persistents.util.CalendarUtil.getDate;

public class SessionValidatorFilterTest {

  private SessionValidatorFilter validatorFilter;

  Clock clock = new Clock() {
    @Override
    public Date now() {
      return getDate(2014, 6, 18, 11, 25);
    }
  };

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private HttpServletResponse response = null;

  @Mock
  private HttpServletRequest request = null;

  @Mock
  private SiteMap siteMap = null;

  @Mock
  private UserSessionsRepository userSessionsRepository = null;

  @Mock
  private FilterChain filterChain = null;

  @Before
  public void setUp() {

    validatorFilter = new SessionValidatorFilter(siteMap, userSessionsRepository, clock);
  }

  @Test
  public void userSessionNotExpired() throws Exception {

    context.checking(new Expectations() {{

      oneOf(request).getRequestURI();
      will(returnValue("mainPage.jsp"));

      oneOf(request).getCookies();

      oneOf(userSessionsRepository).isValidUserSession(null,  getDate(2014, 6, 18, 11, 25));
      will(returnValue(true));

      oneOf(filterChain).doFilter(request, response);

    }
    });

    validatorFilter.doFilter(request, response, filterChain);

  }

  @Test
  public void userSessionWasExpired() throws Exception {

    context.checking(new Expectations() {{

      oneOf(request).getRequestURI();

      oneOf(request).getCookies();

      oneOf(userSessionsRepository).isValidUserSession(null, getDate(2014, 6, 18, 11, 25));
      will(returnValue(false));

      oneOf(siteMap).loginPage();
      will(returnValue("loginPage.jsp"));

      oneOf(response).sendRedirect("loginPage.jsp");
    }
    });

    validatorFilter.doFilter(request, response, filterChain);

  }
}
