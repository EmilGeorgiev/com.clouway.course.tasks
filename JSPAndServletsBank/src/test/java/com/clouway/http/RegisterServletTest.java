package com.clouway.http;

import com.clouway.core.Clock;
import com.clouway.core.SessionID;
import com.clouway.core.SiteMap;
import com.clouway.core.User;
import com.clouway.core.UserDAO;
import com.clouway.core.UserMessages;
import com.clouway.core.ValidationUserData;
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
 * Created by clouway on 6/13/14.
 */
public class RegisterServletTest {

  private RegisterServlet  registerServlet;

  private SessionID sessionID = new SessionID("");
  private User user = new User("ivan", "ivanPass", 1);

  private Clock clock = new Clock() {
    @Override
    public Date now() {
      return CalendarUtil.getDate(2014, 6, 18, 10, 50);
    }
  };

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private HttpServletRequest request = null;

  @Mock
  private HttpServletResponse response = null;

  @Mock
  private SiteMap siteMap = null;

  @Mock
  private UserMessages userMessages = null;

  @Mock
  private UserDAO userDAO = null;

  @Mock
  private ValidationUserData validationUserData = null;


  public static class RequestedUser {
    private final String userName;
    private final String userPassword;

    public RequestedUser(String userName, String userPassword) {
      this.userName = userName;
      this.userPassword = userPassword;
    }
  }

  @Before
  public void setUp() {
    registerServlet = new RegisterServlet(siteMap,
            userMessages,
            userDAO,
            clock,
            validationUserData);
  }

  @Test
  public void registeredNewUser() throws Exception {

    final RequestedUser anyRequestedUser = new RequestedUser("emil", "emilPass");

    pretendThatRequestUserIs(anyRequestedUser);

    context.checking(new Expectations() {{

      oneOf(userDAO).findUser(anyRequestedUser.userName, anyRequestedUser.userPassword);
      will(returnValue(null));

      oneOf(userDAO).register(anyRequestedUser.userName, anyRequestedUser.userPassword, clock.now());
      will(returnValue(sessionID));

      oneOf(response).addCookie(with(any(Cookie.class)));

      oneOf(siteMap).mainPage();
      will(returnValue("mainPage.jsp"));

      oneOf(response).sendRedirect("mainPage.jsp");
    }
    });

    registerServlet.doPost(request, response);

  }



  @Test
  public void registeredUserWithEmployedUserName() throws Exception {

    final RequestedUser anyRequestUser = new RequestedUser("ivan", "ivanPass");

    pretendThatRequestUserIs(anyRequestUser);

    context.checking(new Expectations() {{

      oneOf(userDAO).findUser(anyRequestUser.userName, anyRequestUser.userPassword);
      will(returnValue(user));

      oneOf(siteMap).loginPage();
      will(returnValue("loginPage.jsp"));

      oneOf(response).sendRedirect("loginPage.jsp");
    }
    });

    registerServlet.doPost(request, response);

  }

  private void pretendThatRequestUserIs(final RequestedUser anyRequestedUser) {
    context.checking(new Expectations() {{
      oneOf(userMessages).userName();
      will(returnValue("user_name"));

      oneOf(request).getParameter("user_name");
      will(returnValue(anyRequestedUser.userName));

      oneOf(userMessages).userPassword();
      will(returnValue("user_password"));

      oneOf(request).getParameter("user_password");
      will(returnValue(anyRequestedUser.userPassword));

      oneOf(validationUserData).userNameValidationPattern();
      will(returnValue("^[a-zA-z]{1,15}$"));

      oneOf(validationUserData).passwordValidationPattern();
      will(returnValue("^[a-zA-z0-9]{6,15}$"));
    }
    });
  }
}
