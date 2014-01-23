package com.clouway.calculator;

import com.clouway.calculator.merkarbuttons.MarkerNumbersTest;
import com.clouway.calculator.merkarbuttons.MarkerOperationTest;
import com.clouway.calculator.merkarbuttons.MarkerPointsTest;
import com.clouway.calculator.specificbuttons.CalculatorExpressionTest;
import com.clouway.calculator.specificbuttons.ReducerTextTest;
import com.clouway.calculator.specificbuttons.ResetTextTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by clouway on 1/23/14.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({CalculatorExpressionTest.class, MarkerNumbersTest.class, MarkerOperationTest.class,
MarkerPointsTest.class, ReducerTextTest.class, ResetTextTest.class})
public class SuiteTest {
}
