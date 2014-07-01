package com.clouway.http;

import com.clouway.core.SiteMap;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.script.ScriptEngine;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by clouway on 7/1/14.
 */
public class CalculatorServletTest {

  private CalculatorServlet calculatorServlet;

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private HttpServletRequest request = null;

  @Mock
  private HttpServletResponse response = null;

  @Mock
  private ScriptEngine scriptEngine = null;

  @Mock
  private SiteMap siteMap = null;

  @Before
  public void setUp() {
    calculatorServlet = new CalculatorServlet(siteMap);
  }

  @Test
  public void calculateExpression() throws Exception {

    context.checking(new Expectations() {{

      oneOf(siteMap).expression();
      will(returnValue("expression"));

      oneOf(request).getParameter("expression");
      will(returnValue("5+6"));

      oneOf(scriptEngine).eval("5+6");
      will(returnValue(11.0));
      oneOf(siteMap).result();
      will(returnValue("result"));

      oneOf(request).setAttribute("result", 11.0);

      oneOf(siteMap).calculatorPage();
      will(returnValue("/calculator.jsp"));

      oneOf(request).getRequestDispatcher("/calculator.jsp");

    }
    });

    calculatorServlet.doPost(request, response);

  }
}
