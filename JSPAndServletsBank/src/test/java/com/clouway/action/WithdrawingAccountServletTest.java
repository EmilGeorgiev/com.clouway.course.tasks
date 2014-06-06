package com.clouway.action;

import com.clouway.action.calendarutils.CalendarUtil;
import com.clouway.action.memory.InMemoryBankDAO;
import com.clouway.constants.BankAccountMessages;
import com.clouway.objects.Clock;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Created by clouway on 5/29/14.
 */
public class WithdrawingAccountServletTest {

  private WithdrawingAccountServlet withdrawing;

  private InMemoryBankDAO inMemoryBankDAO;

  Clock clock = new Clock() {
    @Override
    public String now() {
      return CalendarUtil.may(2014, 5, 30).toString();
    }
  };

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private HttpServletRequest request;

  @Mock
  private HttpServletResponse response;

  @Mock
  private BankAccountMessages bankAccountMessages;

  @Mock
  private UserSessionsRepository userSessionsRepository;

  @Before
  public void setUp() {
    inMemoryBankDAO = new InMemoryBankDAO(clock, userSessionsRepository);

    inMemoryBankDAO.deposit(100, "X45CL");

    withdrawing = new WithdrawingAccountServlet(inMemoryBankDAO, bankAccountMessages);
  }

  @Test
  public void whenWithdrawingSameValueThanCurrentAmountDecrementWithThisValue() throws Exception {

    context.checking(new Expectations() {{
      oneOf(bankAccountMessages).withdrawingAmount();
      will(returnValue("withdrawingAmount"));

      oneOf(request).getParameter("withdrawingAmount");
      will(returnValue("30"));

      oneOf(bankAccountMessages).mainPage();
      will(returnValue("mainPage.jsp"));

      oneOf(response).sendRedirect("mainPage.jsp");
    }
    });

    withdrawing.doPost(request, response);

    int result = inMemoryBankDAO.getCurrentUserBankAmount();

    assertThat(result, is(70));
  }

  @Test
  public void whenWithdrawingValueIsLargerThenTheCurrentAmountThanReturnErrorMessage() throws Exception {
    context.checking(new Expectations() {{
      oneOf(bankAccountMessages).withdrawingAmount();
      will(returnValue("withdrawingAmount"));

      oneOf(request).getParameter("withdrawingAmount");
      will(returnValue("150"));

      oneOf(bankAccountMessages).error();
      will(returnValue("Transaction is failed"));

      oneOf(bankAccountMessages).withdrawingPage();
      will(returnValue("withdrawingPage.jsp"));

      oneOf(response).sendRedirect("withdrawingPage.jsp");
    }
    });

    withdrawing.doPost(request, response);

  }
}
