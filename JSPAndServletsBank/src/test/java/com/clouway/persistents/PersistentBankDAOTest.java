package com.clouway.persistents;

import com.clouway.action.calendarutils.CalendarUtil;
import com.clouway.objects.AccountBankDAO;
import com.clouway.objects.Transaction;
import com.clouway.objects.TransactionHistory;
import com.clouway.persistents.fake.InMemoryTransactionHistory;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Created by clouway on 6/3/14.
 */
public class PersistentBankDAOTest {

 // private PersistentBankDAO persistentBankDAO;

  private TransactionHistory transactionHistory;

  private Transaction transaction;

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private AccountBankDAO accountBankDAO;

  @Before
  public void setUp() {
    transactionHistory = new InMemoryTransactionHistory();

   // persistentBankDAO = new PersistentBankDAO();

    transaction = new Transaction("deposit", 1500, CalendarUtil.may(2014, 05, 30).toString(), 1);
  }

  @Test
  public void whenMakeDepositsThenCreateTransactions() throws Exception {
//    context.checking(new Expectations() {{
//      oneOf(depositAccountDAO).deposit(1500, "X45LC45");
//    }
//    });
//
//
//
//    List<Transaction> transactionList = transactionHistory.getAllTransactions();
//
//    assertThat(transactionList, is(Arrays.asList(transaction)));
//
  }
}
