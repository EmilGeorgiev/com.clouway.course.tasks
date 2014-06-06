package com.clouway.action;

import com.clouway.constants.BankAccountMessages;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by clouway on 5/30/14.
 */
public class LoginServletTest {

  private LoginServlet loginServlet;

  //private InMemoryUserDAO inMemoryUserDAO;

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private BankAccountMessages bankAccountMessages;

  @Mock
  private HttpServletResponse response;

  @Mock
  private HttpServletRequest request;


  @Before
  public void setUp() {
//    inMemoryUserDAO = new InMemoryUserDAO();
//
//    loginServlet = new LoginServlet(inMemoryUserDAO, bankAccountMessages);
//
//    inMemoryUserDAO.register("emil", "emil");
  }

  @Test
  public void whenUserIsLoginWhoAlreadyRegisteredThenSendRedirectToMainPage() throws Exception {
//    context.checking(new Expectations(){{
//      oneOf(bankAccountMessages).userName();
//      will(returnValue("user_name"));
//
//      oneOf(request).getParameter("user_name");
//      will(returnValue("emil"));
//
//      oneOf(bankAccountMessages).userPassword();
//      will(returnValue("user_password"));
//
//      oneOf(request).getParameter("user_password");
//      will(returnValue("emil"));
//
//      oneOf(response).sendRedirect("mainPage.jsp");
//    }
//    });
//
//    loginServlet.doPost(request, response);
//
//    boolean isUserExist = inMemoryUserDAO.isUserExist("emil", "emil");
//
//    assertThat(isUserExist, is(true));
  }

//  @Test
//  public void whenUserIsLoggedSuccessThenCreateUniqueSessionNumber() throws Exception {
//
//    context.checking(new Expectations() {{
//
//    }
//    });
//
//    String UUID = mockUserDAO.getUUID(User user);
//
//    assertThat(UUID, );
//
//  }
}
