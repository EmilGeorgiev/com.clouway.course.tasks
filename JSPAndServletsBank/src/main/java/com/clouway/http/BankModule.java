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

      @Override
      public String viewAmount() {
        return "viewAmount";
      }

      @Override
      public String transactionHistory() {
        return "transactionHistory";
      }

      @Override
      public String sid() {
        return "sid";
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
        return "withdrawPage.jsp";
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

      @Override
      public String mainServlet() {
        return "/mainServlet";
      }

      @Override
      public String emptyPage() {
        return "emptyPage.jsp";
      }
    };
  }

  @Provides
  public ValidationUserData getValidationMessages() {
    return new ValidationUserData() {
      @Override
      public String userNameValidationPattern() {
        return "^[a-zA-z]{1,15}$";
      }

      @Override
      public String passwordValidationPattern() {
        return "^[a-zA-z0-9]{1,15}$";
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
