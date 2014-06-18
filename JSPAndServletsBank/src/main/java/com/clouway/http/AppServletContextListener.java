package com.clouway.http;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

/**
 * Created by clouway on 6/6/14.
 */
public class AppServletContextListener extends GuiceServletContextListener {



  @Override
  protected Injector getInjector() {
    return Guice.createInjector(new HttpModule(), new BankModule());
  }
}
