package com.clouway.http;

import com.clouway.core.BankMessage;
import com.clouway.core.BankTransaction;
import com.clouway.core.SiteMap;
import com.clouway.core.User;
import com.clouway.core.UserMessage;
import com.clouway.core.UserRepository;
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

/**
 * Created by clouway on 7/14/14.
 */
public class Sitebricks extends com.google.sitebricks.SitebricksModule {

  private Map<String, BankTransaction> bankTransactionMap = new HashMap<String, BankTransaction>();

  @Override
  protected void configureSitebricks() {

    at("/loginController").show(LoginController.class);
    at("/logoutController").show(LogoutController.class);
    at("/mainController").show(MainController.class);
    at("/registeredController").show(RegisteredController.class);
    at("/transactionController").serve(TransactionController.class);

    embed(ShowTransaction.class).as("Transaction");

    bankTransactionMap.put("deposit", new Deposit(getConnection()));
    bankTransactionMap.put("withdraw", new Withdraw(getConnection()));

  }

  @Provides
  public Map<String, BankTransaction> providesMap() {
    return bankTransactionMap;
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
        return userRepository.findBySessionID(cookie.getValue());
      }
    }

    return null;
  }
}
