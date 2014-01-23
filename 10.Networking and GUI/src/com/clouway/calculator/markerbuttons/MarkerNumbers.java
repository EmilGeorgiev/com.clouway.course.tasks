package com.clouway.calculator.markerbuttons;

import com.clouway.calculator.actinterface.ActionOnText;
import com.clouway.calculator.framecalculator.Calculator;

public class MarkerNumbers implements ActionOnText {
  private final Calculator calculator;

  public MarkerNumbers(Calculator calculator) {
    this.calculator = calculator;
  }

  @Override
  public String addingSymbolInEndOnText(String oldText, String symbol) {
    String newDisplay = oldText + symbol;
    calculator.setLastCharacterOperation(false);
    return newDisplay;
  }
}
