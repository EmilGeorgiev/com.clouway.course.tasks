package com.clouway.calculator.merkarbuttons;

import com.clouway.calculator.framecalculator.Calculator;
import com.clouway.calculator.markerbuttons.MarkerPoints;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by clouway on 1/23/14.
 */
public class MarkerPointsTest {

  Calculator calculator;
  MarkerPoints marker;

  @Before
  public void setUp(){
    calculator = new Calculator();
    marker = new MarkerPoints(calculator);
  }

  @Test
  public void addingOnePointOnNumber() throws Exception {
    String expression = "12";
    String point = ".";
    String actualText = marker.addingSymbolInEndOnText(expression, point);

    assertThat(actualText, is("12."));
  }

  @Test
  public void addingTwoPointOnNumber() throws Exception {
    String expression = "12.";
    calculator.setPointFlag(true);
    String point = ".";
    String actualText = marker.addingSymbolInEndOnText(expression, point);

    assertThat(actualText, is("12."));
  }

  @Test
  public void addingOnePointOnAnotherNumber() throws Exception {
    String expression = "12.0 + 4";
    calculator.setPointFlag(false);
    String point = ".";
    String actualText = marker.addingSymbolInEndOnText(expression, point);

    assertThat(actualText, is("12.0 + 4."));
  }
}
