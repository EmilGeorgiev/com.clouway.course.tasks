package com.clouway.http;

import com.clouway.core.SessionRepository;
import com.clouway.core.SiteMap;
import com.clouway.http.capture.CapturingMatcher;
import com.clouway.util.CalendarUtil;
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

public class SecurityFilterTest {

  private SecurityFilter securityFilter;
  private CalendarUtil clock;
  private Cookie[] cookies;

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

    securityFilter = new SecurityFilter(sessionRepository, siteMap, clock);

    cookies = new Cookie[]{new Cookie("sid", "abc")};

  }

  @Test
  public void authenticateUserWithValidSession() throws Exception {

    context.checking(new Expectations() {{

      oneOf(request).getCookies();
      will(returnValue(with(cookies)));

      oneOf(sessionRepository).authenticate(sessionID("abc"));
      will(returnValue(true));

      oneOf(filterChain).doFilter(request, response);

    }
    });

    securityFilter.doFilter(request, response, filterChain);

  }

  @Test
  public void authenticateUserWithExpireSession() throws Exception {

    final CapturingMatcher<Cookie[]> capturingMatcher =
            new CapturingMatcher<Cookie[]>(Expectations.any(Cookie[].class));


    context.checking(new Expectations() {{

      oneOf(request).getCookies();
      will(returnValue(with(cookies)));

      oneOf(sessionRepository).authenticate(sessionID("abc"));
      will(returnValue(false));

      oneOf(siteMap).loginController();
      will(returnValue("/loginController"));

      oneOf(response).sendRedirect("/loginController");

      oneOf(filterChain).doFilter(request, response);

    }
    });

    securityFilter.doFilter(request, response, filterChain);

  }

  private String sessionID(String session) {
    return session;
  }

}
