package com.clouway.http;

import com.clouway.core.Transaction;
import com.google.sitebricks.SitebricksModule;

/**
 * Created by clouway on 7/14/14.
 */
public class HttpModule extends SitebricksModule {

  @Override
  protected void configureSitebricks() {
    scan(HttpModule.class.getPackage());
    scan(Transaction.class.getPackage());

    at("/transactionController").show(TransactionController.class);

//    bind(BankRepository.class).to(PersistentBankRepository.class);
  }
}
