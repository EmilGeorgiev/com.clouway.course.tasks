package com.clouway.http;

import com.clouway.core.BankAccountMessages;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by clouway on 5/29/14.
 */
public class ViewCurrentAmountServletTest {

  private ViewCurrentAmountServlet viewCurrentAmount;

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private HttpServletResponse response;

  @Mock
  private HttpServletRequest request;

  @Mock
  private BankAccountMessages bankAccountMessages;

//  @Before
//  public void setUp() {
//    inMemoryBankDAO = new InMemoryBankDAO();
//
//    viewCurrentAmount = new ViewCurrentAmountServlet(inMemoryBankDAO, bankAccountMessages);
//
//    inMemoryBankDAO.deposit(20, clock.now());
//    inMemoryBankDAO.deposit(20, clock.now());
//    inMemoryBankDAO.withdraw(10, clock.now());
//  }

//  @Test
//  public void viewCurrentAmountAndHistoryOfTransaction() throws Exception {
//
//    context.checking(new Expectations() {{
//
//      oneOf(bankAccountMessages).currentAccountMessage();
//      will(returnValue("Current amount is: "));
//
//      oneOf(response).sendRedirect("mainPage.jsp");
//    }
//    });

//    viewCurrentAmount.doPost(request, response);
//
//    String message = inMemoryBankDAO.getMessage();
//
//    assertThat(message, is("Current amount is: 30\n" +
//            "Fri May 30 00:00:00 EEST 2014 | 20 | deposit\n" +
//            "Fri May 30 00:00:00 EEST 2014 | 20 | deposit\n" +
//            "Fri May 30 00:00:00 EEST 2014 | 10 | withdraw"));
//
//  }

}
