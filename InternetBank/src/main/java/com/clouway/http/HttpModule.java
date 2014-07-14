package com.clouway.http;

import com.google.sitebricks.SitebricksModule;

/**
 * Created by clouway on 7/14/14.
 */
public class HttpModule extends SitebricksModule {

  @Override
  protected void configureSitebricks() {
    scan(HttpModule.class.getPackage());

//    bind(BankRepository.class).to(PersistentBankRepository.class);
  }
}
