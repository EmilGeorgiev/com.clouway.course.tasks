package com.clouway.http;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

/**
 * Created by clouway on 7/14/14.
 */
public class AppInjector extends GuiceServletContextListener {
  @Override
  protected Injector getInjector() {
    return Guice.createInjector(new SitebricksModule(), new HttpModule());
  }
}
