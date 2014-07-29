package com.clouway.http;

import com.clouway.core.Transaction;
import com.clouway.core.TransactionRepository;
import com.clouway.core.User;
import com.google.inject.util.Providers;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clouway on 7/16/14.
 */
public class MainControllerTest {

  private MainController mainController;

  private User user;

  private List<Transaction> transactionList = new ArrayList<Transaction>();

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private TransactionRepository transactionRepository;

  @Before
  public void setUp() {
    user = new User(userId("34"), sessionID("45th"));

    mainController = new MainController(transactionRepository, Providers.of(user));
  }

  private String sessionID(String sessionID) {
    return sessionID;
  }

  private String userId(String userID) {
    return userID;
  }

  @Test
  public void configureMainPage() throws Exception {

    context.checking(new Expectations() {{
      oneOf(transactionRepository).getAllTransactionsBy(user.getUserName());
      will(returnValue(transactionList));
    }
    });

    mainController.configure();
  }
}
