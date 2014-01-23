package com.clouway.calculator.specificbuttons;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CalculatorExpressionTest {
  @Test
  public void calculatedExpressionWhitOnlyPlusOperation() throws Exception {
    String expression = "5+4+1";
    CalculatorExpression calculatorExpression = new CalculatorExpression();
    String actual = calculatorExpression.malformedExpression(expression);

    assertThat(actual, is("10.0"));
  }

  @Test
  public void calculatedExpressionWhitManyOperation() throws Exception {
    String expression = "5 + 4 + 4/2 + 2*2 - 1";
    CalculatorExpression calculatorExpression = new CalculatorExpression();
    String actual = calculatorExpression.malformedExpression(expression);

    assertThat(actual, is("14.0"));
  }

  @Test
  public void calculatedExpressionLargeNumbers() throws Exception {
    String expression = "9999999999 * 2222222222";
    CalculatorExpression calculatorExpression = new CalculatorExpression();
    String actual = calculatorExpression.malformedExpression(expression);

    assertThat(actual, is("2.222222221777778E19"));
  }
}
