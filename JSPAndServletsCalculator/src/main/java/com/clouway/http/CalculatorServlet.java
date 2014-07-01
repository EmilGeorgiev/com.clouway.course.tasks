package com.clouway.http;

import com.clouway.core.SiteMap;
import com.google.inject.Singleton;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by clouway on 6/27/14.
 */
@Singleton
public class CalculatorServlet extends HttpServlet{
  private final SiteMap siteMap;

  public CalculatorServlet(SiteMap siteMap) {
    this.siteMap = siteMap;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doPost(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    String expression = req.getParameter(siteMap.expression());

    ScriptEngineManager mgr = new ScriptEngineManager();

    ScriptEngine engine = mgr.getEngineByName("JavaScript");

    try {
      Object eval = engine.eval(expression);
      req.setAttribute(siteMap.result(), eval);
    } catch (ScriptException e) {
      e.printStackTrace();
    }

    RequestDispatcher dispatcher = req.getRequestDispatcher(siteMap.calculatorPage());

    dispatcher.forward(req, resp);
  }
}
