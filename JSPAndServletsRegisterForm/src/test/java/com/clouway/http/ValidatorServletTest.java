package com.clouway.http;

import com.clouway.core.InitialMessages;
import com.clouway.core.SiteMap;
import com.clouway.core.UserMessage;
import com.clouway.core.Validator;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ValidatorServletTest {

  private ValidatorServlet validatorServlet;

  private Map<String, String[]> parameters = new HashMap<String, String[]>();
  private Map<String, UserMessage> messageMap = new HashMap<String, UserMessage>();

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private HttpServletResponse response = null;

  @Mock
  private HttpServletRequest request = null;

  @Mock
  private Validator validator = null;

  @Mock
  private InitialMessages initialMessages = null;

  @Mock
  private SiteMap siteMap = null;

  @Before
  public void setUp() {

    validatorServlet = new ValidatorServlet(validator, siteMap);
  }

  @Test
  public void authenticateUserData() throws Exception {

    pretendThatTryRegisterUser(firstName("test"),
                               lastName("test"),
                               egn("1122334455"),
                               age("25"),
                               address("Ivan Vazov"),
                               password("testPass1"));

    context.checking(new Expectations() {{

      oneOf(request).getParameterMap();
      will(returnValue(parameters));

      oneOf(validator).validate(parameters);
      will(returnValue(messageMap));

      oneOf(siteMap).messages();
      will(returnValue("userMessages"));

      oneOf(request).setAttribute("userMessages", messageMap);

      oneOf(siteMap).registerForm();
      will(returnValue("registerForm.jsp"));

      oneOf(request).getRequestDispatcher("registerForm.jsp");
    }
    });

    validatorServlet.doPost(request, response);
  }

  private void pretendThatTryRegisterUser(String firstName,
                                          String lastName,
                                          String egn,
                                          String age,
                                          String address,
                                          String password) {


    parameters.put("first_name", new String[]{firstName});
    parameters.put("last_name", new String[]{lastName});
    parameters.put("user_egn", new String[]{egn});
    parameters.put("user_age", new String[]{age});
    parameters.put("user_address", new String[]{address});
    parameters.put("user_password", new String[]{password});

  }

  private String password(String password) {
    return password;
  }

  private String address(String address) {
    return address;
  }

  private String age(String age) {
    return age;
  }

  private String egn(String egn) {
    return egn;
  }

  private String lastName(String lastName) {
    return lastName;
  }

  private String firstName(String firstName) {
    return firstName;
  }

}
