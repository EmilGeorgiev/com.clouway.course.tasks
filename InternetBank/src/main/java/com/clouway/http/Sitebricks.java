package com.clouway.http;

import com.clouway.core.CurrentUser;
import com.clouway.core.LoginMessages;
import com.clouway.core.RegistrationMessages;
import com.clouway.core.SiteMap;
import com.clouway.core.TransactionMessages;
import com.clouway.core.UserRepository;
import com.google.inject.Provider;
import com.google.inject.Provides;
import com.google.inject.servlet.RequestScoped;
import com.mongodb.DB;
import com.mongodb.MongoClient;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;

public class Sitebricks extends com.google.sitebricks.SitebricksModule {


  @Override
  protected void configureSitebricks() {

    at("/loginController").show(LoginController.class);
    at("/mainController").show(MainController.class);
    at("/registered").show(RegisteredController.class);

    at("/logoutController").serve(LogoutController.class);
    at("/depositController").serve(DepositController.class);
    at("/withdrawController").serve(WithdrawController.class);

    embed(ShowTransaction.class).as("Transaction");

  }

  @Provides
  public SiteMap providesSiteMap() {
    return new SiteMap() {

      @Override
      public String mainController() {
        return "/mainController";
      }

      @Override
      public String sid() {
        return "sid";
      }

      @Override
      public String loginController() {
        return "/loginController";
      }
    };
  }

  @Provides
  public LoginMessages providerLoginMessages() {
    return new LoginMessages() {

      @Override
      public String failed() {
        return "Login is failed.";
      }
    };
  }

  @Provides
  public RegistrationMessages providerRegistrationMessages() {
    return new RegistrationMessages() {
      @Override
      public String success() {
        return "Registration is success and already you can login.";
      }

      @Override
      public String failed() {
        return "Registration is failed";
      }
    };
  }

  @Provides
  public TransactionMessages providerTransactionMessage() {
    return new TransactionMessages() {

      @Override
      public String success() {
        return "Transaction is success.";
      }

      @Override
      public String failed() {
        return "Transaction is failed.";
      }
    };
  }

  @Provides
  public DB getConnection() {
    MongoClient mongoClient = null;
    try {
      mongoClient = new MongoClient();
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }


    if (mongoClient != null) {
      return mongoClient.getDB("Bank");
    }

    return null;

  }

  @Provides
  @RequestScoped
  public CurrentUser getCurrentUser(Provider<HttpServletRequest> requestProvider,
                                    UserRepository userRepository) {
    Cookie[] cookies = requestProvider.get().getCookies();

    if (cookies != null) {
      for (Cookie cookie : cookies) {

        if ("sid".equalsIgnoreCase(cookie.getName())) {
          return userRepository.findBySession(cookie.getValue()).get();
        }
      }
    }

    return null;
  }
}
