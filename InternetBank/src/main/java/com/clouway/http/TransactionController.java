package com.clouway.http;

import com.clouway.core.BankRepository;
import com.clouway.core.Clock;
import com.clouway.core.SiteMap;
import com.clouway.core.Transaction;
import com.clouway.core.TransactionDTO;
import com.clouway.core.User;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.sitebricks.At;
import com.google.sitebricks.headless.Reply;
import com.google.sitebricks.headless.Service;
import com.google.sitebricks.http.Post;

import java.io.IOException;

/**
 * Created by clouway on 7/14/14.
 */
@At("/transactionController")
@Service
public class TransactionController {

  private TransactionDTO transactionDTO = new TransactionDTO();
  private String message;
  private final BankRepository bankRepository;
  private final Clock clock;
  private final Provider<User> currentUser;
  private final SiteMap siteMap;

  @Inject
  public TransactionController(BankRepository bankRepository,
                               Clock clock,
                               Provider<User> currentUser,
                               SiteMap siteMap) {

    this.bankRepository = bankRepository;
    this.clock = clock;
    this.currentUser = currentUser;
    this.siteMap = siteMap;
  }

  @Post
  public Reply<?> transfer() throws IOException {

    Transaction transaction = new Transaction(transactionDTO.getTransactionType(),
            transactionDTO.getAmount(),
            clock.now(),
            currentUser.get().getName());

    message = bankRepository.executeTransaction(transaction);

    String currentAccount = String.valueOf(bankRepository.getAccountBy(currentUser.get().getName()));

    String request = String.format("%s?userMessage=%s&currentAccount=%s&isShowUserMessage=true",
            siteMap.mainController(), message, currentAccount);

    return Reply.saying().redirect(request);

  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public TransactionDTO getTransactionDTO() {
    return transactionDTO;
  }

  public void setTransactionDTO(TransactionDTO transactionDTO) {
    this.transactionDTO = transactionDTO;
  }
}
