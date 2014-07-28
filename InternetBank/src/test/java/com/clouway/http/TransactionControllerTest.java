package com.clouway.http;

import com.clouway.core.BankRepository;
import com.clouway.core.SiteMap;
import com.clouway.core.Transaction;
import com.clouway.core.User;
import com.clouway.http.capture.CapturingMatcher;
import com.clouway.util.CalendarUtil;
import com.google.inject.util.Providers;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by clouway on 7/14/14.
 */
public class TransactionControllerTest {

  private TransactionController transactionController;
  private Transaction transaction;

  private CalendarUtil clock = new CalendarUtil(2014, 7, 14, 15, 46, 34);

  User user;

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private BankRepository bankRepository = null;

  @Mock
  private SiteMap siteMap = null;

  @Mock
  private HttpServletResponse response;

  @Before
  public void setUp() {

    user = new User(userId("23"), sessionID("45XQ"));
    transactionController = new TransactionController(bankRepository, clock, Providers.of(user), siteMap, response);
  }

  private String sessionID(String sessionID) {
    return sessionID;
  }

  @Test
  public void transactionSomeValue() throws Exception {

    final CapturingMatcher<Transaction> capturingMatcher =
            new CapturingMatcher<Transaction>(Expectations.any(Transaction.class));

    context.checking(new Expectations() {{

      oneOf(bankRepository).makeTransaction(with(capturingMatcher));
    }
    });

    transactionController.transfer();

  }

  private String userId(String userId) {
    return userId;
  }
}
