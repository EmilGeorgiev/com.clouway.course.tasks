package com.clouway.http;

import com.clouway.core.SessionRepository;
import com.clouway.core.User;
import com.google.inject.util.Providers;
import com.google.sitebricks.At;
import com.google.sitebricks.Show;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Created by clouway on 7/17/14.
 */
@At("/logoutController")
@Show("LoginController.html")
public class LogoutControllerTest {

  private LogoutController logoutController;

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private SessionRepository sessionRepository = null;

  @Before
  public void setUp() {

    User currentUser = new User(name("test"), password("testPass"), id(23), session("45tg"));

    logoutController = new LogoutController(sessionRepository, Providers.of(currentUser));
  }

  @Test
  public void logoutUser() throws Exception {

    context.checking(new Expectations() {{

      oneOf(sessionRepository).deleteSessionByID("45tg");
    }
    });

    logoutController.logout();

  }

  private Object id(int id) {
    return id;
  }

  private String password(String password) {
    return password;
  }

  private String name(String name) {
    return name;
  }

  private String session(String session) {
    return session;
  }
}
