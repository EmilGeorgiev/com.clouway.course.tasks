package com.clouway.http;

import com.clouway.core.SessionRepository;
import com.clouway.core.User;
import com.google.inject.util.Providers;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


public class LogoutControllerTest {

  private LogoutController logoutController;

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private SessionRepository sessionRepository = null;

  @Before
  public void setUp() {

    User currentUser = new User(session("123"), name("test"));

    logoutController = new LogoutController(sessionRepository, Providers.of(currentUser));
  }

  @Test
  public void logoutUser() throws Exception {

    context.checking(new Expectations() {{

      oneOf(sessionRepository).deleteSessionByID("123");
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
