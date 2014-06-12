package com.clouway.alltest;

import com.clouway.http.LoginServletTest;
import com.clouway.http.LogoutServletTest;
import com.clouway.http.SessionValidatorFilterTest;
import com.clouway.http.TransactionServletTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by clouway on 5/29/14.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({LoginServletTest.class, TransactionServletTest.class, LogoutServletTest.class,
                    SessionValidatorFilterTest.class})
public class AllTest {
}
