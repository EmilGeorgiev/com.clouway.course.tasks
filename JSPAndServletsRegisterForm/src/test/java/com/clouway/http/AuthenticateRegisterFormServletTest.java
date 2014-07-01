package com.clouway.http;

import com.clouway.core.AuthorizationFormData;
import com.clouway.core.RegisterFormMessages;
import com.clouway.core.SiteMap;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by clouway on 6/30/14.
 */
public class AuthenticateRegisterFormServletTest {

  private AuthenticateRegisterFormServlet authenticateRegisterFormServlet;

  private List<String> parameterNames = new ArrayList<String>();

  private List<String> parameterValues = new ArrayList<String>();

  private Map<String, String> parameterNameRegex = new HashMap<String, String>();

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private HttpServletResponse response = null;

  @Mock
  private HttpServletRequest request = null;

  @Mock
  private AuthorizationFormData authorizationFormData = null;

  @Mock
  private RegisterFormMessages registerFormMessages = null;

  @Mock
  private SiteMap siteMap = null;

  @Before
  public void setUp() {
    parameterNames.add("first_name");
    parameterNames.add("last_name");
    parameterNames.add("user_egn");
    parameterNames.add("user_age");
    parameterNames.add("user_address");
    parameterNames.add("user_password");

    parameterNameRegex.put("first_name", "^[a-zA-z]{1,15}$");
    parameterNameRegex.put("last_name", "^[a-zA-z]{1,15}$");
    parameterNameRegex.put("user_egn", "^[0-9]{10}$");
    parameterNameRegex.put("user_age", "^1[8-9]$|^[2-9][0-9]$|^10[0-9]$|^11[1-8]$");
    parameterNameRegex.put("user_address", "^[\\W\\w]{1,100}$");
    parameterNameRegex.put("user_password", "^[a-zA-z0-9]{6,15}$");

    authenticateRegisterFormServlet = new AuthenticateRegisterFormServlet(authorizationFormData,
                                                                          siteMap,
                                                                          registerFormMessages);

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
      oneOf(request).getParameterNames();
      will(returnValue(any(with(Enumeration.class))));

      expectInvokeExpectation();

      oneOf(siteMap).registerForm();
      will(returnValue("registerForm.jsp"));

      oneOf(request).getRequestDispatcher("registerForm.jsp");
    }
    });

    authenticateRegisterFormServlet.doPost(request, response);
  }

  private void expectInvokeExpectation() {

    for (int i = 0; i < 6; i++) {
      final int index = i;
      context.checking(new Expectations() {
        {

          String parameterName = parameterNames.get(index);

          String parameterValue = parameterValues.get(index);

          String regex = parameterNameRegex.get(parameterName);

          oneOf(request).getParameter(parameterName);
          will(returnValue(parameterValue));

          oneOf(authorizationFormData).getRegexForParameter(parameterName);
          will(returnValue(regex));

          oneOf(authorizationFormData).isUserDataMatchToRegex(parameterValue, regex);
          will(returnValue(true));

          oneOf(request).setAttribute(parameterName, "correct");

//          oneOf(registerFormMessages).value();
//          will(returnValue("Value"));

          oneOf(request).setAttribute(parameterName +  "Value"/*registerFormMessages.value()*/, parameterName);
        }
      });
    }


  }

  private void pretendThatTryRegisterUser(String firstName,
                                          String lastName,
                                          String egn,
                                          String age,
                                          String address,
                                          String password) {

    parameterValues.add(firstName);
    parameterValues.add(lastName);
    parameterValues.add(egn);
    parameterValues.add(age);
    parameterValues.add(address);
    parameterValues.add(password);

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
