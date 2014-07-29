package com.clouway.http;

import com.clouway.core.BankRepository;
import com.clouway.core.SiteMap;
import com.clouway.core.Transaction;
import com.clouway.core.TransactionDTO;
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by clouway on 7/14/14.
 */
public class TransactionControllerTest {

  private TransactionController transactionController;

  private CalendarUtil clock = new CalendarUtil(2014, 7, 14, 15, 46, 34);

  User user;

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private BankRepository bankRepository = null;

  @Mock
  private SiteMap siteMap = null;

  @Before
  public void setUp() {

    TransactionDTO transactionDTO = new TransactionDTO("deposit", 50);

    user = new User(userId("23"), userName("test"));
    transactionController = new TransactionController(bankRepository,
                                                      clock,
                                                      Providers.of(user),
                                                      siteMap);

    transactionController.setTransactionDTO(transactionDTO);
  }

  @Test
  public void transactionSomeValue() throws Exception {

    final CapturingMatcher<Transaction> capturingMatcher =
            new CapturingMatcher<Transaction>(Expectations.any(Transaction.class));

    context.checking(new Expectations() {{

      oneOf(siteMap).mainController();
      will(returnValue("/mainController"));

      oneOf(bankRepository).executeTransaction(with(capturingMatcher));
      will(returnValue("success"));
    }
    });



    String request = transactionController.transfer();

    assertThat(request, is("/mainController?userMessage=success"));
  }

  private String userName(String sessionID) {
    return sessionID;
  }

  private String userId(String userId) {
    return userId;
  }
}
