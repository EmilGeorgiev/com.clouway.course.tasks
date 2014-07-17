package com.clouway.http;

import com.clouway.core.*;
import com.clouway.persistent.PersistentBankRepository;
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
public class SitebricksModule extends com.google.sitebricks.SitebricksModule {

  @Override
  protected void configureSitebricks() {
    scan(SitebricksModule.class.getPackage());
    scan(Transaction.class.getPackage());

    bind(BankRepository.class).to(PersistentBankRepository.class);

  }


  @Provides
  public SiteMap providesSiteMap() {
    return new SiteMap() {
      @Override
      public String loginPage() {
        return "LoginController.html";
      }

      @Override
      public String mainPage() {
        return "MainController.html";
      }

      @Override
      public String mainController() {
        return "/mainController";
      }

      @Override
      public String sid() {
        return "sid";
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
  public DBMessage providerDBMessages() {
    return new DBMessage() {
      @Override
      public String collectionUser() {
        return "user";
      }

      @Override
      public String fieldAccount() {
        return "account";
      }

      @Override
      public String fieldId() {
        return "_id";
      }

      @Override
      public String operatorInc() {
        return "$inc";
      }

      @Override
      public String fieldTransactionType() {
        return "transactionType";
      }

      @Override
      public String fieldAmount() {
        return "amount";
      }

      @Override
      public String fieldDate() {
        return "date";
      }

      @Override
      public String fieldUserId() {
        return "user_id";
      }

      @Override
      public String collectionTransaction() {
        return "transaction";
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
  public User getCurrentUser(Provider<HttpServletRequest> requestProvider, SessionRepository sessionRepository, Clock clock) {
    Cookie[] cookies = requestProvider.get().getCookies();
    for (Cookie cookie : cookies) {
      // session id
      if ("sid".equalsIgnoreCase(cookie.getName())) {
        return sessionRepository.authenticateSession(cookie.getValue(), clock);
      }
    }

    return null;
  }
}
