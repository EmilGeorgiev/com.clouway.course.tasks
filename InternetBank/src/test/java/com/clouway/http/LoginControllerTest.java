package com.clouway.http;

import com.clouway.core.SiteMap;
import com.clouway.core.UserDTO;
import com.clouway.core.UserRepository;
import com.clouway.http.capture.CapturingMatcher;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by clouway on 7/17/14.
 */
public class LoginControllerTest {

  private LoginController loginController;
  private UserDTO userDTO = new UserDTO();

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private HttpServletResponse response = null;

  @Mock
  private SiteMap siteMap = null;

  @Mock
  private UserRepository userRepository = null;

  @Before
  public void setUp(){
    loginController = new LoginController(userRepository, response, siteMap);
  }

  @Test
  public void loginUser() throws Exception {

    final CapturingMatcher<Cookie> capturingMatcher =
            new CapturingMatcher<Cookie>(Expectations.any(Cookie.class));

    context.checking(new Expectations() {{

      oneOf(userRepository).findUser(userDTO);
      will(returnValue(sid("12345")));

      oneOf(siteMap).sid();
      will(returnValue("sid"));

      oneOf(response).addCookie(with(capturingMatcher));

      oneOf(siteMap).mainController();
      will(returnValue("/mainController"));

    }
    });

    loginController.login();

  }

  @Test
  public void loginUserWithInvalidData() throws Exception {

    context.checking(new Expectations() {{
      oneOf(userRepository).findUser(userDTO);
      will(returnValue(null));

      oneOf(siteMap).loginController();
      will(returnValue("/loginController"));
    }
    });

    loginController.login();
  }

  private String sid(String sessionID) {
    return sessionID;
  }
}
