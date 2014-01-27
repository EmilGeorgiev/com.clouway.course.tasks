package com.clouway.calculator;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;
import sun.util.resources.CalendarData_sl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Created by clouway on 1/24/14.
 */
public class CalculatorTest {

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  CalculatorView view;

  @Test
  public void addingNumberOnCalculatorDisplay() throws Exception {
    Calculator calculator = new Calculator(view);

    context.checking(new Expectations() {{
      oneOf(view).getExpression();
      oneOf(view).setExpression("5");
      will(returnValue("5"));
    }
    });
    String actualExpression = calculator.onNumberPressed('5');

    assertThat(actualExpression, is("5"));
  }

  @Test
  public void addingOperationAfterNumber() throws Exception {
    Calculator calculator = new Calculator(view);
    context.checking(new Expectations() {{
      oneOf(view).getExpression();
      will(returnValue("5"));
      oneOf(view).setExpression("5+");
      will(returnValue("5+"));
    }
    });

    String actualExpression = calculator.onOperationPressed('+');

    assertThat(actualExpression, is("5+"));
  }

  @Test
  public void addingOperationAfterOperation() throws Exception {
    Calculator calculator = new Calculator(view);

    context.checking(new Expectations() {{
      oneOf(view).getExpression();
      will(returnValue("5"));
      oneOf(view).setExpression("5+");
      oneOf(view).getExpression();
      will(returnValue("5+"));
    }
    });

    calculator.onOperationPressed('+');
    String actualExpression = calculator.onOperationPressed('+');

    assertThat(actualExpression, is("5+"));
  }

  @Test
  public void backspace() throws Exception {
    Calculator calculator = new Calculator(view);

    context.checking(new Expectations() {{
      oneOf(view).getExpression();
      will(returnValue("5+4"));
      oneOf(view).setExpression("5+");
      will(returnValue("5+"));
    }
    });

    String actualExpression = calculator.onBackspacePressed();

    assertThat(actualExpression, is("5+"));
  }

  @Test
  public void addingTwoPointInNumber() throws Exception {
    Calculator calculator = new Calculator(view);

    context.checking(new Expectations(){{
      oneOf(view).getExpression();
      will(returnValue("4"));
      oneOf(view).setExpression("4" + ".");
      will(returnValue("4."));
      oneOf(view).getExpression();
      will(returnValue("4."));
    }
    });

    calculator.onPointPressed();
    String actualExpression = calculator.onPointPressed();

    assertThat(actualExpression, is("4."));
  }

  @Test
  public void deletedExpression() throws Exception {
    Calculator calculator = new Calculator(view);

    context.checking(new Expectations(){{
      oneOf(view).setExpression("");
      will(returnValue(""));
    }
    });

    String actualExpression = calculator.onDeletedPressed();

    assertThat(actualExpression, is(""));
  }
}
