package com.clouway.suite;

import com.clouway.http.AuthenticatedFilterTest;
import com.clouway.http.LoginControllerTest;
import com.clouway.http.LogoutControllerTest;
import com.clouway.http.MainControllerTest;
import com.clouway.http.RegisteredControllerTest;
import com.clouway.http.TransactionControllerTest;
import com.clouway.persistent.PersistentBankRepositoryTest;
import com.clouway.persistent.PersistentSessionRepositoryTest;
import com.clouway.persistent.PersistentUserRepositoryTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by clouway on 7/17/14.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({AuthenticatedFilterTest.class,
        LoginControllerTest.class,
        LogoutControllerTest.class,
        MainControllerTest.class,
        RegisteredControllerTest.class,
        TransactionControllerTest.class,
        PersistentBankRepositoryTest.class,
        PersistentSessionRepositoryTest.class,
        PersistentUserRepositoryTest.class
        })
public class SuiteTests {
}
