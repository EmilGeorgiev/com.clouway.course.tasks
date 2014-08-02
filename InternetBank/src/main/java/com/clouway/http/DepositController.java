package com.clouway.http;

import com.clouway.core.BankRepository;
import com.clouway.core.SiteMap;
import com.clouway.core.TransactionInfo;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.sitebricks.At;
import com.google.sitebricks.headless.Reply;
import com.google.sitebricks.headless.Service;
import com.google.sitebricks.http.Post;

/**
 * @author Emil Georgiev <emogeorgiev88@gmail.com>
 */
@At("/depositController")
@Service
@Singleton
public class DepositController {

  private final BankRepository bankRepository;
  private final SiteMap siteMap;
  private Double amount;

  @Inject
  public DepositController(BankRepository bankRepository, SiteMap siteMap) {

    this.bankRepository = bankRepository;
    this.siteMap = siteMap;
  }

  @Post
  public Reply<?> deposit() {
    TransactionInfo info = bankRepository.deposit(amount);
    return Reply.saying().redirect(siteMap.mainController()+ "?isShowUserMessage=true&userMessage=" + info.getMessage());
  }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
