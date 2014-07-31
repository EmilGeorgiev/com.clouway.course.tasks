package com.clouway.http;

import com.clouway.core.*;
import com.clouway.http.capture.CapturingMatcher;
import com.clouway.util.CalendarUtil;
import com.google.inject.util.Providers;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by clouway on 7/14/14.
 */
public class TransactionControllerTest {

  private TransactionController transactionController;

  private Map<String, TransactionEntityFactory> factoryMap = new HashMap<String, TransactionEntityFactory>();

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

    factoryMap.put("deposit", new DepositCreator());
    factoryMap.put("withdraw", new WithdrawEntityFactory());

    user = new User(userId("23"), userName("test"));
    transactionController = new TransactionController(Providers.of(factoryMap),
                                                      clock,
                                                      Providers.of(user),
                                                      siteMap,
                                                      bankRepository);

    transactionController.setTransactionDTO(transactionDTO);
  }

  @Test
  public void transactionSomeValue() throws Exception {

    final CapturingMatcher<TransactionEntity> capturingMatcher =
            new CapturingMatcher<TransactionEntity>(Expectations.any(TransactionEntity.class));

    context.checking(new Expectations() {{

      oneOf(siteMap).mainController();
      will(returnValue("/mainController"));

      oneOf(bankRepository).updateBalance(with(capturingMatcher));
      will(returnValue("success&currentAmount=45"));

    }
    });

    transactionController.transfer();

  }

  private String userName(String sessionID) {
    return sessionID;
  }

  private String userId(String userId) {
    return userId;
  }
}
