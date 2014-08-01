package com.clouway.http;

import com.google.sitebricks.At;
import com.google.sitebricks.headless.Service;

@At("/transactionController")
@Service
public class TransactionController {

//  private TransactionDTO transactionDTO = new TransactionDTO();
//  private final Clock clock;
//  private final Provider<CurrentUser> currentUser;
//  private final SiteMap siteMap;
//  private final BankRepository bankRepository;
//
//  @Inject
//  public TransactionController(Clock clock,
//                               Provider<CurrentUser> currentUser,
//                               SiteMap siteMap,
//                               BankRepository bankRepository) {
//
//    this.clock = clock;
//    this.currentUser = currentUser;
//    this.siteMap = siteMap;
//    this.bankRepository = bankRepository;
//  }
//
//  @Post
//  public Reply<?> transfer() throws IOException {
//
//    Transaction transaction = new Transaction(transactionDTO.getType(),
//            transactionDTO.getAmount(),
//            clock.now(),
//            currentUser.get().getName());
//
//    TransactionInfo transactionInfo = bankRepository.updateBalance(transaction);
//
//    String request = String.format("%s?userMessage=%s&currentAmount=%s&isShowUserMessage=true",
//            siteMap.mainController(), transactionInfo.getMessage(), transactionInfo.getCurrentAmount());
//
//    return Reply.saying().redirect(request);
//
//  }
//
//  public TransactionDTO getTransactionDTO() {
//    return transactionDTO;
//  }
//
//  public void setTransactionDTO(TransactionDTO transactionDTO) {
//    this.transactionDTO = transactionDTO;
//  }
}

