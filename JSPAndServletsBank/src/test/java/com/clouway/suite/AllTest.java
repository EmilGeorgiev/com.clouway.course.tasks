package com.clouway.suite;

import com.clouway.http.LoginServletTest;
import com.clouway.http.LogoutServletTest;
import com.clouway.http.RegisterServletTest;
import com.clouway.http.SessionValidatorFilterTest;
import com.clouway.http.TransactionServletTest;
import com.clouway.persistents.PersistentBankDAOTest;
import com.clouway.persistents.PersistentUserDAOTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by clouway on 5/29/14.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({LoginServletTest.class,
                      LogoutServletTest.class,
                      RegisterServletTest.class,
                      SessionValidatorFilterTest.class,
                      TransactionServletTest.class,
                      PersistentBankDAOTest.class,
                      PersistentUserDAOTest.class
                   })
public class AllTest {
}
