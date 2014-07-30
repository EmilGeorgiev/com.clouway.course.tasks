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
import java.util.HashMap;
import java.util.Map;

public class Sitebricks extends com.google.sitebricks.SitebricksModule {

    private Map<String, TransactionFactory> transactionHandlerMap = new HashMap<String, TransactionFactory>();

   @Override
  protected void configureSitebricks() {

    at("/loginController").show(LoginController.class);
    at("/mainController").show(MainController.class);
    at("/registered").show(RegisteredController.class);

    at("/logoutController").serve(LogoutController.class);
    at("/transactionController").serve(TransactionController.class);

    embed(ShowTransaction.class).as("Transaction");

    transactionHandlerMap.put("deposit", new DepositFactory());
    transactionHandlerMap.put("withdraw", new WithdrawFactory());

  }

    @Provides
    public Map<String, TransactionFactory> providerMapTransactionHandler() {
        return transactionHandlerMap;
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
        return userRepository.findBySessionID(cookie.getValue());
      }
    }

    return null;
  }
}
