package com.clouway.http;

import com.clouway.core.AuthenticateService;
import com.clouway.core.Clock;
import com.clouway.core.SessionID;
import com.clouway.core.SiteMap;
import com.clouway.core.UserMessages;
import com.clouway.persistents.util.CalendarUtil;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by clouway on 5/30/14.
 */
public class LoginServletTest {

  private LoginServlet loginServlet;
  private SessionID sessionID;

  Clock clock = new Clock() {
    @Override
    public Date now() {
      return CalendarUtil.getDate(2014, 6, 18, 10, 50);
    }
  };

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private HttpServletResponse response = null;

  @Mock
  private HttpServletRequest request = null;

  @Mock
  private UserMessages userMessages = null;

  @Mock
  private SiteMap siteMap = null;

  @Mock
  private AuthenticateService authenticateService = null;

  public static class RequestedUser {
    public final String user;
    public final String pass;

    RequestedUser(String user, String pass) {
      this.user = user;
      this.pass = pass;
    }
  }


  @Before
  public void setUp() {

    loginServlet = new LoginServlet(userMessages, siteMap, authenticateService, clock);

    sessionID = new SessionID("");

  }

  @Test
  public void loginWithExistingUser() throws Exception {
    final RequestedUser anyRequestedUser = new RequestedUser("emil", "emilPass");

    pretendThatRequestedUserIs(anyRequestedUser);

    context.checking(new Expectations() {{

      oneOf(authenticateService).authenticate("emil", "emilPass", clock.now());
      will(returnValue(sessionID));

      oneOf(response).addCookie(with(any(Cookie.class)));

      oneOf(siteMap).mainServlet();
      will(returnValue("completeAttributeServlet"));

      oneOf(request).getRequestDispatcher("/completeAttributeServlet");

    }
    });

    loginServlet.doPost(request, response);
  }



  @Test
  public void loginWithNotExistingUser() throws Exception {
    final RequestedUser requestedUser = new RequestedUser("ivan", "ivanPass");
    pretendThatRequestedUserIs(requestedUser);

    context.checking(new Expectations() {{

      oneOf(authenticateService).authenticate(requestedUser.user, requestedUser.pass, clock.now());
      will(returnValue(null));

      oneOf(siteMap).loginPage();
      will(returnValue("loginPage.jsp"));

      oneOf(response).sendRedirect("loginPage.jsp");
    }
    });

    loginServlet.doPost(request, response);

  }

  private void pretendThatRequestedUserIs(final RequestedUser requestedUser) {
    context.checking(new Expectations() {{

      oneOf(userMessages).userName();
      will(returnValue("user_name"));

      oneOf(request).getParameter("user_name");
      will(returnValue(requestedUser.user));

      oneOf(userMessages).userPassword();
      will(returnValue("user_password"));

      oneOf(request).getParameter("user_password");
      will(returnValue(requestedUser.pass));
    }
    });
  }
}
