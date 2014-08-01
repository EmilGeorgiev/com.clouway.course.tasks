package com.clouway.http;

/**
 * Created by clouway on 7/14/14.
 */
public class TransactionControllerTest {

//  private TransactionController transactionController;
//
//  private CalendarUtil clock = new CalendarUtil(2014, 7, 14, 15, 46, 34);
//
//  private CurrentUser currentUser;
//
//  private TransactionInfo transactionInfo;
//
//  @Rule
//  public JUnitRuleMockery context = new JUnitRuleMockery();
//
//  @Mock
//  private BankRepository bankRepository = null;
//
//  @Mock
//  private SiteMap siteMap = null;
//
//  @Before
//  public void setUp() {
//
//    TransactionDTO transactionDTO = new TransactionDTO(DEPOSIT, 50);
//
//
//    currentUser = new CurrentUser(userName("test"));
//    transactionController = new TransactionController(clock,
//                                                      Providers.of(currentUser),
//                                                      siteMap,
//                                                      bankRepository);
//
//    transactionController.setTransactionDTO(transactionDTO);
//
//    transactionInfo = new TransactionInfo(userName("test"), amount(23.0));
//  }
//
//  private Double amount(double amount) {
//    return amount;
//  }
//
//  @Test
//  public void transactionSomeValue() throws Exception {
//
//    final CapturingMatcher<Transaction> capturingMatcher =
//            new CapturingMatcher<Transaction>(Expectations.any(Transaction.class));
//
//    context.checking(new Expectations() {{
//
//      oneOf(siteMap).mainController();
//      will(returnValue("/mainController"));
//
//      oneOf(bankRepository).updateBalance(with(capturingMatcher));
//      will(returnValue(transactionInfo));
//
//    }
//    });
//
//    transactionController.transfer();
//
//  }
//
//  private String userName(String sessionID) {
//    return sessionID;
//  }
//
//  private String userId(String userId) {
//    return userId;
//  }
}
