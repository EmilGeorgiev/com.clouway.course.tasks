package com.clouway.http;

import com.clouway.core.LoginMessages;
import com.clouway.core.SiteMap;
import com.clouway.core.User;
import com.clouway.core.UserRepository;
import com.clouway.http.capture.CapturingMatcher;
import com.google.common.base.Optional;
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
  private User user = new User("test", "testPass");
  private UserDTO userDTO = new UserDTO("test", "testPass");
  private Optional<String> optionalSID = Optional.fromNullable("123");

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private HttpServletResponse response = null;

  @Mock
  private SiteMap siteMap = null;

  @Mock
  private UserRepository userRepository = null;

  @Mock
  private LoginMessages loginMessages = null;

  @Before
  public void setUp(){
    loginController = new LoginController(userRepository, response, siteMap, loginMessages);

    loginController.setUserDTO(userDTO);
  }

  @Test
  public void loginUser() throws Exception {

    final CapturingMatcher<Cookie> capturingMatcher =
            new CapturingMatcher<Cookie>(Expectations.any(Cookie.class));

    context.checking(new Expectations() {{

      oneOf(userRepository).login(user);
      will(returnValue(optionalSID));

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
      oneOf(userRepository).login(user);
      will(returnValue(Optional.absent()));

      oneOf(loginMessages).failed();
      will(returnValue("Login is failed"));

    }
    });

    loginController.login();
  }


}
