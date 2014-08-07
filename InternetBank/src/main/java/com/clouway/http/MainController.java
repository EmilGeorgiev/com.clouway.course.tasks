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
  private final BankRepository bankRepository;

  private List<TransactionEntity> list;
  private String userMessage;
  private Double currentAmount = 0.0;
  private Boolean isShowUserMessage = false;
  private String userName;

  @Inject
  public MainController(TransactionRepository transactionRepository,
                        BankRepository bankRepository,
                        Provider<CurrentUser> currentUser) {

    this.transactionRepository = transactionRepository;

    this.bankRepository = bankRepository;
    this.userName = currentUser.get().getName();
  }

  /**
   * Retrieve all transaction on current user.
   */
  @Get
  public void configure() {

    currentAmount = bankRepository.getCurrentAmount();
    list = transactionRepository.getUserTransactions();

  }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getIsShowUserMessage() {
    return isShowUserMessage;
  }

  public void setIsShowUserMessage(String isShowUserMessage) {

    this.isShowUserMessage = Boolean.valueOf(isShowUserMessage);
  }

  public Double getCurrentAmount() {
      return currentAmount;
  }



  public void setCurrentAmount(Double currentAmount) {
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
