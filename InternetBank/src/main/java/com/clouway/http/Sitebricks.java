package com.clouway.http;

import com.clouway.core.*;
import com.google.inject.Provider;
import com.google.inject.Provides;
import com.google.inject.servlet.RequestScoped;
import com.mongodb.DB;
import com.mongodb.MongoClient;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;

/**
 * Created by clouway on 7/14/14.
 */
public class Sitebricks extends com.google.sitebricks.SitebricksModule {

  @Override
  protected void configureSitebricks() {

    at("/loginController").show(LoginController.class);
    at("/logoutController").show(LogoutController.class);
    at("/mainController").show(MainController.class);
    at("/registeredController").show(RegisteredController.class);
    at("/transactionController").serve(TransactionController.class);

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
  public UserMessage providerUserMessage() {
    return new UserMessage() {
      @Override
      public String failed() {
        return "User name or password already exist";
      }

      @Override
      public String success() {
        return "Registration is success.Now you can login.";
      }
    };
  }

  @Provides
  public BankMessage providerBankMessage() {
    return new BankMessage() {
      @Override
      public String deposit() {
        return "deposit";
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

    assert mongoClient != null;
    return mongoClient.getDB("Bank");

  }


  @Provides
  @RequestScoped
  public User getCurrentUser(Provider<HttpServletRequest> requestProvider,
                                    UserRepository userRepository) {
    Cookie[] cookies = requestProvider.get().getCookies();
    for (Cookie cookie : cookies) {

      if ("sid".equalsIgnoreCase(cookie.getName())) {
        return userRepository.findUserBySessionID(cookie.getValue());
      }
    }

    return null;
  }
}
