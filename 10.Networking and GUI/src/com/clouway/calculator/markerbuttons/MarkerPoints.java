package com.clouway.calculator.markerbuttons;

import com.clouway.calculator.actinterface.ActionOnText;
import com.clouway.calculator.framecalculator.Calculator;

/**
 * Created by clouway on 1/23/14.
 */
public class MarkerPoints implements ActionOnText {

  private final Calculator calculator;

  public MarkerPoints(Calculator calculator) {

    this.calculator = calculator;
  }

  @Override
  public String addingSymbolInEndOnText(String oldText, String symbol) {
    String newDisplay = oldText;
    if (!calculator.isPointFlag()) {
          newDisplay += symbol;
          calculator.setPointFlag(true);
        }
    return newDisplay;
  }
}
