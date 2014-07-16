package com.clouway.http;

import com.clouway.core.BankRepository;
import com.clouway.core.Transaction;
import com.clouway.persistent.PersistentBankRepository;
import com.google.inject.Provides;
import com.google.sitebricks.SitebricksModule;
import com.mongodb.DB;
import com.mongodb.MongoClient;

import java.net.UnknownHostException;

/**
 * Created by clouway on 7/14/14.
 */
public class HttpModule extends SitebricksModule {

  @Override
  protected void configureSitebricks() {
    scan(HttpModule.class.getPackage());
    scan(Transaction.class.getPackage());

    bind(BankRepository.class).to(PersistentBankRepository.class);

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

//    @Provides
//  @RequestScoped
//  public CurrentUser getCurrentUser(Provider<HttpServletRequest> requestProvider,
//                                    UserSessionsRepository userSessionsRepository,
//                                    Clock clock) {
//
//    return new CurrentUser(new User(23));
//  }

//  @Provides
//  @RequestScoped
//  public CurrentUser getCurrentUser(Provider<HttpServletRequest> requestProvider, UserSessionsRepository userSessionsRepository, Clock clock) {
//    Cookie[] cookies = requestProvider.get().getCookies();
//    for (Cookie cookie : cookies) {
//      // session id
//      if ("sid".equalsIgnoreCase(cookie.getName())) {
//        User user = userSessionsRepository.isUserExistBySession(cookie.getValue(), clock);
//        return new CurrentUser(user);
//      }
//    }
//
//    return new CurrentUser(null);
//  }
}
