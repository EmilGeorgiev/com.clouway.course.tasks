package com.clouway;

import com.google.inject.servlet.ServletModule;

/**
 * Created by clouway on 6/27/14.
 */
public class CalculatorModule extends ServletModule {
  @Override
  protected void configureServlets() {
    serve("/calculatorServlet").with(CalculatorServlet.class);
  }
}
