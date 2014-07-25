package com.clouway.http;

import com.clouway.core.BankRepository;
import com.clouway.core.Clock;
import com.clouway.core.Transaction;
import com.clouway.core.TransactionDTO;
import com.clouway.core.User;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.sitebricks.At;
import com.google.sitebricks.Show;
import com.google.sitebricks.http.Post;

/**
 * Created by clouway on 7/14/14.
 */
@At("/transactionController")
@Show("MainController")
public class TransactionController {

  private TransactionDTO transactionDTO = new TransactionDTO();
  private String message;
  private final BankRepository bankRepository;
  private final Clock clock;
  private final Provider<User> currentUser;


  @Inject
  public TransactionController(BankRepository bankRepository,
                               Clock clock,
                               Provider<User> currentUser) {
    this.bankRepository = bankRepository;
    this.clock = clock;
    this.currentUser = currentUser;
  }

  @Post
  public void transfer(){

    Transaction transaction = new Transaction(transactionDTO.getTransactionType(),
                                              transactionDTO.getAmount(),
                                              clock.now(),
                                              currentUser.get().getUserName());

    bankRepository.makeTransaction(transaction);

    message = "Transaction success";
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
