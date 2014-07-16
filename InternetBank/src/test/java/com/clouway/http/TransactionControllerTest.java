package com.clouway.http;

import com.clouway.core.BankRepository;
import com.clouway.core.CurrentUser;
import com.clouway.core.Transaction;
import com.clouway.core.User;
import com.clouway.http.util.CalendarUtil;
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
  private Transaction transaction = new Transaction();

  private CalendarUtil clock = new CalendarUtil(2014, 7, 14, 15, 46);
  CurrentUser currentUser;

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private BankRepository bankRepository = null;

  public TransactionControllerTest() {
  }


  @Before
  public void setUp() {

    currentUser = new CurrentUser(new User(23));
    transactionController = new TransactionController(bankRepository);
  }

  @Test
  public void depositSomeValue() throws Exception {

    context.checking(new Expectations() {{

      oneOf(bankRepository).makeTransaction(transaction);
    }
    });

    transactionController.doPost();

  }
}
