package com.clouway.http;

import com.clouway.core.BankRepository;
import com.clouway.core.Clock;
import com.clouway.core.SessionRepository;
import com.clouway.core.Time;
import com.clouway.core.TransactionRepository;
import com.clouway.core.UserRepository;
import com.clouway.persistent.PersistentBankRepository;
import com.clouway.persistent.PersistentSessionRepository;
import com.clouway.persistent.PersistentUserRepository;
import com.google.inject.servlet.ServletModule;

/**
 * Created by clouway on 7/16/14.
 */
public class HttpModule extends ServletModule {

  @Override
  protected void configureServlets() {

    bind(BankRepository.class).to(PersistentBankRepository.class);
    bind(TransactionRepository.class).to(PersistentBankRepository.class);
    bind(UserRepository.class).to(PersistentUserRepository.class);
    bind(SessionRepository.class).to(PersistentSessionRepository.class);
    bind(Clock.class).to(Time.class);

//    filter("/*").through(AuthenticatedFilter.class);
    filter("^/(?!registeredController)([a-zA-Z]+)$ ").through(AuthenticatedFilter.class);


  }
}
