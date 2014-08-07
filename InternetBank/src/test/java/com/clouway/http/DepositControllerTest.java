package com.clouway.http;

import com.clouway.core.BankRepository;
import com.clouway.core.SiteMap;
import com.clouway.core.TransactionInfo;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Emil Georgiev <emogeorgiev88@gmail.com>
 */
public class DepositControllerTest {

  private DepositController depositController;
  private Double amountDTO = 20.0;
  private TransactionInfo transactionInfo;

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private BankRepository bankRepository = null;

  @Mock
  private SiteMap siteMap = null;

  @Before
  public void setUp() {
    depositController = new DepositController(bankRepository, siteMap);

    transactionInfo = new TransactionInfo("success");

    depositController.setAmount(20.0);

  }

  @Test
  public void depositSomeValue() throws Exception {

    context.checking(new Expectations() {{

      oneOf(bankRepository).deposit(20.0);
      will(returnValue(transactionInfo));

      oneOf(siteMap).mainController();
      will(returnValue("/mainController"));
    }
    });

    depositController.deposit();

  }
}
