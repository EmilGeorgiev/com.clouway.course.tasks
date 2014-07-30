package com.clouway.http;

import com.clouway.core.*;
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
  private final SiteMap siteMap;
  private List<TransactionEntity> list;
  private User user;
  private String userMessage;
  private String currentAccount;
  private Boolean isShowUserMessage = false;

  @Inject
  public MainController(TransactionRepository transactionRepository,
                        Provider<User> userProvider,
                        SiteMap siteMap) {

    this.transactionRepository = transactionRepository;
    this.userProvider = userProvider;
    this.siteMap = siteMap;
  }

  /**
   * Retrieve all transaction on current user.
   */
  @Get
  public String configure() {

    user = userProvider.get();
    if(user == null) {
      return siteMap.loginController();
    }
    list = transactionRepository.getAllTransactionsBy(user.getName());

    return null;
  }

  public Boolean getIsShowUserMessage() {
    return isShowUserMessage;
  }

  public void setIsShowUserMessage(String isShowUserMessage) {

    this.isShowUserMessage = Boolean.valueOf(isShowUserMessage);
  }

  public String getCurrentAccount() {
    return currentAccount;
  }

  public void setCurrentAccount(String currentAccount) {
    this.currentAccount = currentAccount;
  }

  public List<TransactionEntity> getList() {
    return list;
  }

  public void setList(List<TransactionEntity> list) {
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
