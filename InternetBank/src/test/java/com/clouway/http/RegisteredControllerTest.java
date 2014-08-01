package com.clouway.http;

import com.clouway.core.RegistrationInfo;
import com.clouway.core.User;
import com.clouway.core.UserRepository;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Created by clouway on 7/16/14.
 */
public class RegisteredControllerTest {

  private RegisteredController registeredController;
  private User user = new User("ivan", "ivanPass");
  private UserDTO userDTO = new UserDTO("ivan", "ivanPass");
  private RegistrationInfo registrationInfo;

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private UserRepository userRepository = null;

  @Before
  public void setUp() {
    registeredController = new RegisteredController(userRepository);

    registeredController.setUserDTO(userDTO);

    registrationInfo = new RegistrationInfo("Registration is done.");
  }

  @Test
  public void registerNewUser() throws Exception {

    context.checking(new Expectations(){{

      oneOf(userRepository).register(user);
      will(returnValue(registrationInfo));

    }
    });

    registeredController.registered();
  }

}
