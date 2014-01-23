package com.clouway.calculator.specificbuttons;

import com.clouway.calculator.actinterface.MalformedText;
import com.clouway.calculator.framecalculator.Calculator;

/**
 * Created by clouway on 1/23/14.
 */
public class ResetText implements MalformedText {
  private final Calculator calculator;

  public ResetText(Calculator calculator) {

    this.calculator = calculator;
  }
  @Override
  public String malformedExpression(String expression) {
    String newDisplay = "";
    calculator.setLastCharacterOperation(false);
    calculator.setPointFlag(false);
    return newDisplay;
  }
}
