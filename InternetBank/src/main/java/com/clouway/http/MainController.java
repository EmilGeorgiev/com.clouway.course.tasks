package com.clouway.http;

import com.clouway.core.*;
import com.google.common.base.Optional;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.sitebricks.At;
import com.google.sitebricks.http.Get;

import java.util.List;

@At("/mainController")
@Singleton
public class MainController {

  public final TransactionRepository transactionRepository;
  public final Provider<Optional<CurrentUser>> currentUserProvider;
  private List<TransactionEntity> list;
  private String userMessage;
  private String currentAmount;
  private Boolean isShowUserMessage = false;

  @Inject
  public MainController(TransactionRepository transactionRepository,
                        Provider<Optional<CurrentUser>> currentUserProvider) {

    this.transactionRepository = transactionRepository;
    this.currentUserProvider = currentUserProvider;
  }

  /**
   * Retrieve all transaction on current user.
   */
  @Get
  public String configure() {

    Optional<CurrentUser> optional = currentUserProvider.get();

    if (optional.isPresent()) {

      list = transactionRepository.getUserTransactions();
    }


    return null;
  }

  public Boolean getIsShowUserMessage() {
    return isShowUserMessage;
  }

  public void setIsShowUserMessage(String isShowUserMessage) {

    this.isShowUserMessage = Boolean.valueOf(isShowUserMessage);
  }

  public String getCurrentAmount() {
    return currentAmount;
  }

  public void setCurrentAmount(String currentAmount) {
    this.currentAmount = currentAmount;
  }

  public List<TransactionEntity> getList() {
    return list;
  }

  public void setList(List<TransactionEntity> list) {
    this.list = list;
  }

  public String getUserMessage() {
    return userMessage;
  }

  public void setUserMessage(String userMessage) {
    this.userMessage = userMessage;
  }
}
