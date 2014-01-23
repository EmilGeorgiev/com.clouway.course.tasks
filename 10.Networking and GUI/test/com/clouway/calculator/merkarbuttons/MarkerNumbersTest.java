package com.clouway.calculator.merkarbuttons;

import com.clouway.calculator.framecalculator.Calculator;
import com.clouway.calculator.markerbuttons.MarkerNumbers;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MarkerNumbersTest {

  @Test
  public void addingNumberToText() throws Exception {
    Calculator calculator = new Calculator();
    String oldText = "4+4";
    String symbol = "5";
    MarkerNumbers marker = new MarkerNumbers(calculator);
    String actual = marker.addingSymbolInEndOnText(oldText, symbol);

    assertThat(actual, is("4+45"));
  }
}
