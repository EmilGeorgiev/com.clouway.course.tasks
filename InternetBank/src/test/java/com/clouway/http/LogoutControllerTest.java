package com.clouway.http;

import com.clouway.core.SiteMap;
import com.clouway.http.capture.CapturingMatcher;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


public class LogoutControllerTest {

  private LogoutController logoutController;

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private HttpServletRequest request = null;

  @Mock
  private SiteMap siteMap = null;

  @Before
  public void setUp() {

    logoutController = new LogoutController(request, siteMap);
  }

  @Test
  public void logoutUser() throws Exception {

    final CapturingMatcher<Cookie[]> capturingMatcher =
            new CapturingMatcher<Cookie[]>(Expectations.any(Cookie[].class));

    context.checking(new Expectations() {{

      oneOf(request).getCookies();
      will(returnValue(with(capturingMatcher)));

      oneOf(siteMap).loginController();
      will(returnValue("/loginController"));
    }
    });

    logoutController.logout();

  }

  private String name(String name) {
    return name;
  }

  private String session(String session) {
    return session;
  }
}
