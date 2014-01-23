package com.clouway.calculator.specificbuttons;

import com.clouway.calculator.actinterface.MalformedText;

/**
 * Created by clouway on 1/23/14.
 */
public class ReducerText implements MalformedText {

  @Override
  public String malformedExpression(String expression) {
    String newDisplay = expression;
    if (newDisplay.length() != 0) {
      String subString = newDisplay.substring(0, newDisplay.length() - 1);
      newDisplay = subString;
    }
    return newDisplay;
  }
}
