package com.clouway.http;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

import java.sql.Connection;

/**
 * Created by clouway on 6/6/14.
 */
public class AppServletContextListener extends GuiceServletContextListener {
  @Override
  protected Injector getInjector() {
    return Guice.createInjector(new ServletModule() {
      @Override
      protected void configureServlets() {

      }

      @Provides
      public Connection getConnection() {
        return ConnectionPerRequestFilter.CONNECTION.get();
      }
    });
  }
}
