package com.clouway.http;

import com.clouway.core.BankRepository;
import com.clouway.core.Transaction;
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
  private Transaction transaction;

  private CalendarUtil clock = new CalendarUtil(2014, 7, 14, 15, 46);

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private BankRepository bankRepository;

  public TransactionControllerTest() {
  }


  @Before
  public void setUp() {

    transactionController = new TransactionController(/*bankRepository*/);
  }

  @Test
  public void depositSomeValue() throws Exception {
    pretendThatMakeTransaction(transactionType("deposit"), amount(20), currentAmount(100));

    context.checking(new Expectations() {{
      oneOf(bankRepository).makeTransaction(transaction);
    }
    });

    transactionController.doPost();

  }

  private void pretendThatMakeTransaction(String transactionType, float amount, float currentAmount) {
    transaction = new Transaction();
  }

  private float currentAmount(float currentAmount) {
    return currentAmount;
  }

  private float amount(float amount) {
    return amount;
  }

  private String transactionType(String transaction) {
    return transaction;
  }
}
