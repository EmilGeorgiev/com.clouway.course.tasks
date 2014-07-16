package com.clouway.http;

import com.clouway.core.BankRepository;
import com.clouway.core.Transaction;
import com.clouway.core.User;
import com.clouway.util.CalendarUtil;
import com.google.inject.util.Providers;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Created by clouway on 7/14/14.
 */
public class TransactionControllerTest {

  private TransactionController transactionController;
  private Transaction transaction;

  private CalendarUtil clock = new CalendarUtil(2014, 7, 14, 15, 46);

  User user;

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private BankRepository bankRepository = null;

  @Before
  public void setUp() {

    user = new User(name("test"), password("testPass"), userId(23));
    transactionController = new TransactionController(bankRepository, clock, Providers.of(user));
  }

  @Test
  public void depositSomeValue() throws Exception {

    context.checking(new Expectations() {{

      oneOf(bankRepository).makeTransaction(transaction);
    }
    });

    transactionController.transfer();

  }

  private Object userId(int userId) {
    return userId;
  }

  private String password(String password) {
    return password;
  }

  private String name(String name) {
    return name;
  }
}
