package com.clouway.http;

import com.clouway.core.SiteMap;
import com.google.inject.Provides;
import com.google.inject.servlet.ServletModule;

/**
 * Created by clouway on 6/27/14.
 */
public class CalculatorModule extends ServletModule {
  @Override
  protected void configureServlets() {
    serve("/calculatorServlet").with(CalculatorServlet.class);
  }

  @Provides
  public SiteMap getSiteMap() {
    return new SiteMap() {
      @Override
      public String expression() {
        return "expression";
      }

      @Override
      public String result() {
        return "result";
      }

      @Override
      public String calculatorPage() {
        return "/calculator.jsp";
      }
    };
  }
}
