package com.clouway.http;

import com.clouway.core.UserDTO;
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
  private UserDTO userDTO = new UserDTO();

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private UserRepository userRepository = null;

  @Before
  public void setUp() {
    registeredController = new RegisteredController(userRepository);
  }

  @Test
  public void attemptRegisterUser() throws Exception {

    context.checking(new Expectations(){{

      oneOf(userRepository).registerUserIfNotExist(userDTO);
      will(returnValue("Registration is done."));

    }
    });

    registeredController.registered();
  }

}
