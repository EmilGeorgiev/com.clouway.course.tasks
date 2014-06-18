package com.clouway.http;

import com.clouway.core.AuthenticateService;
import com.clouway.core.SiteMap;
import com.clouway.core.SessionID;
import com.clouway.core.UserMessages;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by clouway on 5/30/14.
 */
public class LoginServletTest {

  private LoginServlet loginServlet;
  private SessionID sessionID;

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

    loginServlet = new LoginServlet(userMessages, siteMap, authenticateService);

    sessionID = new SessionID("XL4562GD");

  }

  @Test
  public void loginWithExistingUser() throws Exception {
    final RequestedUser anyRequestedUser = new RequestedUser("emil", "emilpass");

    pretendThatRequestedUserIs(anyRequestedUser);

    context.checking(new Expectations() {{

      oneOf(authenticateService).authenticate("emil", "emilpass");
      will(returnValue(sessionID));

      oneOf(response).addCookie(with(any(Cookie.class)));

      oneOf(siteMap).mainPage();
      will(returnValue("mainPage.jsp"));

      oneOf(response).sendRedirect("mainPage.jsp");
    }
    });

    loginServlet.doPost(request, response);
  }



  @Test
  public void loginWithNotExistingUser() throws Exception {
    final RequestedUser requestedUser = new RequestedUser("ivan", "ivanpass");
    pretendThatRequestedUserIs(requestedUser);

    context.checking(new Expectations() {{

      oneOf(authenticateService).authenticate(requestedUser.user, requestedUser.pass);
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
