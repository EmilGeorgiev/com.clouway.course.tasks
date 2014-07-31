package com.clouway.http;

import com.clouway.core.BankRepository;
import com.clouway.core.Clock;
import com.clouway.core.Result;
import com.clouway.core.SiteMap;
import com.clouway.core.Transaction;
import com.clouway.core.User;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.sitebricks.At;
import com.google.sitebricks.headless.Reply;
import com.google.sitebricks.headless.Service;
import com.google.sitebricks.http.Post;

import java.io.IOException;

@At("/transactionController")
@Service
public class TransactionController {

  private TransactionDTO transactionDTO = new TransactionDTO();
  private final Clock clock;
  private final Provider<User> currentUser;
  private final SiteMap siteMap;
  private final BankRepository bankRepository;

  @Inject
  public TransactionController(Clock clock,
                               Provider<User> currentUser,
                               SiteMap siteMap,
                               BankRepository bankRepository) {

    this.clock = clock;
    this.currentUser = currentUser;
    this.siteMap = siteMap;
    this.bankRepository = bankRepository;
  }

  @Post
  public Reply<?> transfer() throws IOException {

    Transaction transaction = new Transaction(transactionDTO.getType(),
            transactionDTO.getAmount(),
            clock.now(),
            currentUser.get().getName());

    Result result = bankRepository.updateBalance(transaction);

    String request = String.format("%s?userMessage=%s&currentAmount=%s&isShowUserMessage=true",
            siteMap.mainController(), result.getMessage(), result.getCurrentAmount());

    return Reply.saying().redirect(request);

  }

  public void setTransactionDTO(TransactionDTO transactionDTO) {
    this.transactionDTO = transactionDTO;
  }
}

