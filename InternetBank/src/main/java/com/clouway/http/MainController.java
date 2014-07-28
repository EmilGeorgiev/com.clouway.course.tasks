package com.clouway.http;

import com.clouway.core.Transaction;
import com.clouway.core.TransactionRepository;
import com.clouway.core.User;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.sitebricks.At;
import com.google.sitebricks.http.Get;

import java.util.List;

@At("/mainController")
@Singleton
public class MainController {
  private final TransactionRepository transactionRepository;
  private final Provider<User> userProvider;
  private List<Transaction> list;
  private User user;
  private String userMessage;

  @Inject
  public MainController(TransactionRepository transactionRepository, Provider<User> userProvider) {

    this.transactionRepository = transactionRepository;
    this.userProvider = userProvider;
  }

  /**
   * Retrieve all transaction on current user.
   */
  @Get
  public void configure() {

    user = userProvider.get();
    list = transactionRepository.getAllTransactionByUserName(user.getUserName());
  }

  public List<Transaction> getList() {
    return list;
  }

  public void setList(List<Transaction> list) {
    this.list = list;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getUserMessage() {
    return userMessage;
  }

  public void setUserMessage(String userMessage) {
    this.userMessage = userMessage;
  }
}
