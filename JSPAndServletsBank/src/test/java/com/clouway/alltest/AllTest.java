package com.clouway.alltest;

import com.clouway.http.down.DepositAccountServletTest;
import com.clouway.http.LoginServletTest;
import com.clouway.http.ViewCurrentAmountServletTest;
import com.clouway.http.down.WithdrawingAccountServletTest;
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
