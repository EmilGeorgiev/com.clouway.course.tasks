package com.clouway.http;

import com.clouway.core.*;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.sitebricks.At;
import com.google.sitebricks.headless.Reply;
import com.google.sitebricks.headless.Service;
import com.google.sitebricks.http.Post;

import java.io.IOException;
import java.util.Map;

@At("/transactionController")
@Service
public class TransactionController {

  private TransactionDTO transactionDTO = new TransactionDTO();
  private String message;
  private final Provider<Map<String, TransactionFactory>> provider;
  private final Clock clock;
  private final Provider<User> currentUser;
  private final SiteMap siteMap;
    private final BankRepository bankRepository;

    @Inject
  public TransactionController(Provider<Map<String, TransactionFactory>> provider,
                               Clock clock,
                               Provider<User> currentUser,
                               SiteMap siteMap,
                               BankRepository bankRepository) {

    this.provider = provider;
    this.clock = clock;
    this.currentUser = currentUser;
    this.siteMap = siteMap;
        this.bankRepository = bankRepository;
    }

  @Post
  public Reply<?> transfer() throws IOException {

    Transaction transaction = new Transaction(transactionDTO.getTransactionType(),
            transactionDTO.getAmount(),
            clock.now(),
            currentUser.get().getName());

    TransactionFactory factory = provider.get().get(transactionDTO.getTransactionType());

    TransactionEntity transactionEntity = factory.create(transaction);

    message = bankRepository.updateBalance(transactionEntity);

    String request = String.format("%s?userMessage=%s&isShowUserMessage=true",
            siteMap.mainController(), message);

    return Reply.saying().redirect(request);

  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public void setTransactionDTO(TransactionDTO transactionDTO) {
    this.transactionDTO = transactionDTO;
  }
}

