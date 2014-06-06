package com.clouway.alltest;

import com.clouway.action.DepositAccountServletTest;
import com.clouway.action.LoginServletTest;
import com.clouway.action.ViewCurrentAmountServletTest;
import com.clouway.action.WithdrawingAccountServletTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by clouway on 5/29/14.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({DepositAccountServletTest.class, WithdrawingAccountServletTest.class
                      , ViewCurrentAmountServletTest.class, LoginServletTest.class})
public class AllTest {
}
