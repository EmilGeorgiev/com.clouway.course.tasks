package com.clouway;

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

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doPost(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String expression = req.getParameter("expression");

    ScriptEngineManager mgr = new ScriptEngineManager();

    ScriptEngine engine = mgr.getEngineByName("JavaScript");



    try {
      Object eval = engine.eval(expression);
      req.setAttribute("result", eval);
    } catch (ScriptException e) {
      e.printStackTrace();
    }

    RequestDispatcher dispatcher = req.getRequestDispatcher("/calculator.jsp");

    dispatcher.forward(req, resp);
  }
}
