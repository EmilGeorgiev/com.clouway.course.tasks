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

/**
 * Created by clouway on 7/17/14.
 */
public class LogoutControllerTest {

  private LogoutController logoutController;

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private SessionRepository sessionRepository = null;

  @Before
  public void setUp() {

    User currentUser = new User(id("123"), session("321"));

    logoutController = new LogoutController(sessionRepository, Providers.of(currentUser));
  }

  @Test
  public void logoutUser() throws Exception {

    context.checking(new Expectations() {{

      oneOf(sessionRepository).deleteSessionByID("321");
    }
    });

    logoutController.logout();

  }

  private String id(String id) {
    return id;
  }

  private String session(String session) {
    return session;
  }
}
