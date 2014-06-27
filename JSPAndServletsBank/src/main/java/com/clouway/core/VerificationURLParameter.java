package com.clouway.core;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by clouway on 6/26/14.
 */
@Singleton
public class VerificationURLParameter implements AuthorizationURLParameters {

  private final BankAccountMessages bankAccountMessages;
  private final SiteMap siteMap;
  private Map<String, String> webPages = new HashMap<String, String>();

  @Inject
  public VerificationURLParameter(BankAccountMessages bankAccountMessages, SiteMap siteMap) {
    this.bankAccountMessages = bankAccountMessages;
    this.siteMap = siteMap;
    includePagesInMap();

  }

  private void includePagesInMap() {
    webPages.put(bankAccountMessages.deposit(), siteMap.depositPage());
    webPages.put(bankAccountMessages.withdraw(), siteMap.withdrawingPage());
    webPages.put(bankAccountMessages.viewAmount(), siteMap.viewAmountPage());
  }


  @Override
  public String getWebPageIfParameterIsAuthorize(String urlParameter) {
    if (webPages.containsKey(urlParameter)) {
      return webPages.get(urlParameter);
    }
    return null;
  }
}
