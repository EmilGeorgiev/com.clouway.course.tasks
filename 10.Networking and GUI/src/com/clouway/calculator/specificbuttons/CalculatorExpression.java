package com.clouway.calculator.specificbuttons;

import com.clouway.calculator.actinterface.MalformedText;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class CalculatorExpression implements MalformedText {

  public String malformedExpression(String expression) {
    ScriptEngineManager mgr = new ScriptEngineManager();
    ScriptEngine engine = mgr.getEngineByName("JavaScript");
    Double result = null;
    try {
      result = (Double) engine.eval(expression);
    } catch (ScriptException e) {
      e.printStackTrace();
    }
    return result.toString();
  }
}
