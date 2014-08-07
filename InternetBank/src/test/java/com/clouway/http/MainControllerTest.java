package com.clouway.http;

import com.clouway.core.BankRepository;
import com.clouway.core.CurrentUser;
import com.clouway.core.Transaction;
import com.clouway.core.TransactionRepository;
import com.google.common.base.Optional;
import com.google.inject.util.Providers;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class MainControllerTest {

  private MainController mainController;

  private CurrentUser currentUser;

  private List<Transaction> transactionList = new ArrayList<Transaction>();

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private TransactionRepository transactionRepository = null;

  @Mock
  private BankRepository bankRepository = null;

  @Before
  public void setUp() {
    currentUser = new CurrentUser(name("ivan"));



    mainController = new MainController(transactionRepository, bankRepository, Providers.of(currentUser));
  }

  @Test
  public void configureMainPage() throws Exception {

    context.checking(new Expectations() {{

      oneOf(bankRepository).getCurrentAmount();
      will(returnValue(45.0));

      oneOf(transactionRepository).getUserTransactions();
      will(returnValue(transactionList));
    }
    });

    mainController.configure();
  }

  private String name(String name) {
    return name;
  }
}
