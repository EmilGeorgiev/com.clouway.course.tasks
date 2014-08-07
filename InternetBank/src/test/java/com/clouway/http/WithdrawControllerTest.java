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
 * Created by HOME on 3.8.2014 г..
 */
public class WithdrawControllerTest {

    private WithdrawController withdrawController;
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
        withdrawController = new WithdrawController(bankRepository, siteMap);

        transactionInfo = new TransactionInfo("success");

        withdrawController.setAmount(20.0);

    }

    @Test
    public void depositSomeValue() throws Exception {

        context.checking(new Expectations() {{

            oneOf(bankRepository).withdraw(20.0);
            will(returnValue(transactionInfo));

            oneOf(siteMap).mainController();
            will(returnValue("/mainController"));
        }
        });

        withdrawController.withdraw();

    }
}
