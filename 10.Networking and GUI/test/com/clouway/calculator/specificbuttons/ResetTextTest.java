package com.clouway.calculator.specificbuttons;

import com.clouway.calculator.framecalculator.Calculator;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by clouway on 1/23/14.
 */
public class ResetTextTest {

  @Test
  public void deletedAllText() throws Exception {
    Calculator calculator = new Calculator();
    ResetText reset = new ResetText(calculator);
    String text = "4+3/2";
    String actualText = reset.malformedExpression(text);

    assertThat(actualText, is(""));
  }
}
