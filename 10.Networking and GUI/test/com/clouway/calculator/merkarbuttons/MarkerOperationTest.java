package com.clouway.calculator.merkarbuttons;

import com.clouway.calculator.framecalculator.Calculator;
import com.clouway.calculator.markerbuttons.MarkerOperation;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by clouway on 1/23/14.
 */
public class MarkerOperationTest {

  @Test
  public void addingOperationAfterNumberOnText() throws Exception {
    Calculator calculator = new Calculator();
    MarkerOperation marker = new MarkerOperation(calculator);
    String expression = "45";
    String operation = "+";
    String actualText = marker.addingSymbolInEndOnText(expression, operation);

    assertThat(actualText, is("45+"));
  }

  @Test
  public void addingOperationAfterOperationOnText() throws Exception {
    Calculator calculator = new Calculator();
    calculator.setLastCharacterOperation(true);
    MarkerOperation marker = new MarkerOperation(calculator);
    String expression = "45-";
    String operation = "+";
    String actualText = marker.addingSymbolInEndOnText(expression, operation);

    assertThat(actualText, is("45-"));
  }
}
