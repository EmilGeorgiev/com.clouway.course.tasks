package com.clouway.http;

import com.clouway.core.BankRepository;
import com.clouway.core.Clock;
import com.clouway.core.SessionRepository;
import com.clouway.core.SystemClock;
import com.clouway.core.TransactionRepository;
import com.clouway.core.UserRepository;
import com.clouway.persistent.PersistentBankRepository;
import com.clouway.persistent.PersistentUserRepository;
import com.google.inject.servlet.ServletModule;

public class HttpModule extends ServletModule {

  @Override
  protected void configureServlets() {

    bind(BankRepository.class).to(PersistentBankRepository.class);
    bind(TransactionRepository.class).to(PersistentBankRepository.class);
    bind(UserRepository.class).to(PersistentUserRepository.class);
    bind(SessionRepository.class).to(PersistentUserRepository.class);

    bind(Clock.class).to(SystemClock.class);

    filter("/re").through(SecurityFilter.class);
  }
}
