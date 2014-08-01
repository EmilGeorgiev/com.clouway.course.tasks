package com.clouway.http;

import com.clouway.core.BankRepository;
import com.clouway.core.SiteMap;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.sitebricks.http.Post;

/**
 * @author Emil Georgiev <emogeorgiev88@gmail.com>
 */
@Singleton
public class DepositController {

  private final BankRepository bankRepository;
  private final SiteMap siteMap;

  @Inject
  public DepositController(BankRepository bankRepository, SiteMap siteMap) {

    this.bankRepository = bankRepository;
    this.siteMap = siteMap;
  }

  @Post
  public String deposit() {
    return null;
  }
}
