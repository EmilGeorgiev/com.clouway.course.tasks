package com.clouway.http;

import com.google.inject.servlet.ServletModule;

/**
 * Created by clouway on 7/16/14.
 */
public class HttpModule extends ServletModule {

  @Override
  protected void configureServlets() {
    filter("/*").through(AuthenticatedFilter.class);
  }
}
