package com.clouway.calculator.specificbuttons;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by clouway on 1/23/14.
 */
public class ReducerTextTest {

  @Test
  public void reducedText() throws Exception {
    ReducerText reducer = new ReducerText();
    String text = "2+4-";
    String actualText = reducer.malformedExpression(text);

    assertThat(actualText, is("2+4"));
  }
}
