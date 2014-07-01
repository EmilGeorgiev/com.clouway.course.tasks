package com.clouway.http;

import com.clouway.core.AuthorizationFormData;
import com.clouway.core.ErrorMessages;
import com.clouway.core.RegexMessages;
import com.clouway.core.RegisterFormMessages;
import com.clouway.core.SiteMap;
import com.clouway.core.UserDataValidator;
import com.google.inject.Provides;
import com.google.inject.servlet.ServletModule;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by clouway on 6/26/14.
 */
public class AuthenticateModule extends ServletModule {

  private Map<String, String> formMessages = new HashMap<String, String>();
  private Map<String, String> regexMessages = new HashMap<String, String>();

  @Override
  protected void configureServlets() {

    serve("/authenticateServlet").with(AuthenticateRegisterFormServlet.class);

    filter("/registerForm.jsp").through(RegisterFilter.class);

    bind(AuthorizationFormData.class).to(UserDataValidator.class);

  }

  @Provides
  public ErrorMessages getErrorMessages() {
    return new ErrorMessages() {
      @Override
      public String wrong() {
        return "wrong";
      }

      @Override
      public String correct() {
        return "correct";
      }
    };
  }

  @Provides
  public RegexMessages getRegexMessages() {

    regexMessages.put("first_name", "^[a-zA-z]{1,15}$");
    regexMessages.put("last_name", "^[a-zA-z]{1,15}$");
    regexMessages.put("user_age", "^1[8-9]$|^[2-9][0-9]$|^10[0-9]$|^11[1-8]$");
    regexMessages.put("user_egn", "^[0-9]{10}$");
    regexMessages.put("user_address", "^[\\W\\w]{1,100}$");
    regexMessages.put("user_password", "^[a-zA-z0-9]{6,15}$");

    return new RegexMessages() {

      @Override
      public String getRegexForParameter(String parameterName) {
        return regexMessages.get(parameterName);
      }
    };
  }

  @Provides
  public SiteMap getSiteMap() {
    return new SiteMap() {
      @Override
      public String registerForm() {
        return "/registerForm.jsp";
      }
    };
  }

  @Provides
  public RegisterFormMessages getRegisterFormMessages() {

    formMessages.put("first_name", "Contains form 1 to 15 symbols");
    formMessages.put("last_name", "Contains form 1 to 15 symbols");
    formMessages.put("user_egn", "Exactly 10 digits");
    formMessages.put("user_age", "From 10 to 118");
    formMessages.put("user_address", "From 1 to 100");
    formMessages.put("user_password", "From 6 to 18 symbols and contains only latin letters and numbers");

    return new RegisterFormMessages() {
      @Override
      public Map<String, String> getMessages() {
        return formMessages;
      }

      @Override
      public String value() {
        return "Value";
      }

      @Override
      public String emptyValue() {
        return "";
      }

    };
  }
}