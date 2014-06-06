package com.clouway.alltest;

import com.clouway.core.DepositAccountServletTest;
import com.clouway.core.LoginServletTest;
import com.clouway.core.ViewCurrentAmountServletTest;
import com.clouway.core.WithdrawingAccountServletTest;
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
