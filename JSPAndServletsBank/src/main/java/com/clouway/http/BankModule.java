package com.clouway.http;

import com.clouway.core.*;
import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.Provides;
import com.google.inject.servlet.RequestScoped;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by clouway on 6/2/14.
 */
public class BankModule extends AbstractModule {

  @Override
  protected void configure() {

  }

  @Provides
  public BankAccountMessages getBankAccountMessages() {
    return new BankAccountMessages() {

      @Override
      public String withdraw() {
        return "withdraw";
      }

      @Override
      public String transactionAmount() {
        return "transactionAmount";
      }

      @Override
      public String transactionType() {
        return "transactionType";
      }

      @Override
      public String deposit() {
        return "deposit";
      }

      @Override
      public String currentAmount() {
        return "currentAmount";
      }

    };
  }

  @Provides
  public UserMessages getUserMessages() {
    return new UserMessages() {
      @Override
      public String userName() {
        return "user_name";
      }

      @Override
      public String userPassword() {
        return "user_password";
      }
    };
  }

  @Provides
  public SiteMap getPageMessages() {
    return new SiteMap() {
      @Override
      public String mainPage() {
        return "mainPage.jsp";
      }

      @Override
      public String loginPage() {
        return "loginPage.jsp";
      }

      @Override
      public String register() {
        return "registeredPage.jsp";
      }

      @Override
      public String withdrawingPage() {
        return "withdrawingPage.jsp";
      }

      @Override
      public String depositPage() {
        return "depositPage.jsp";
      }

      @Override
      public String viewAmountPage() {
        return "viewAmountPage.jsp";
      }

      @Override
      public String contentPage() {
        return "contentPage";
      }
    };
  }

  @Provides
  public ValidationMessages getValidationMessages() {
    return new ValidationMessages() {
      @Override
      public String userNameValidationPattern() {
        return null;
      }

      @Override
      public String passwordValidationPattern() {
        return null;
      }
    };
  }

  @Provides
  @RequestScoped
  public CurrentUser getCurrentUser(Provider<HttpServletRequest> requestProvider, UserSessionsRepository userSessionsRepository, Clock clock) {
    Cookie[] cookies = requestProvider.get().getCookies();
    for (Cookie cookie : cookies) {
      // session id
      if ("sid".equalsIgnoreCase(cookie.getName())) {

        User user = userSessionsRepository.isUserExistBySession(cookie.getValue(), clock);
        return new CurrentUser(user);
      }
    }

    return new CurrentUser(null);
  }

}
