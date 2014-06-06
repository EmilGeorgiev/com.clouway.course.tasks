package com.clouway.persistents;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import java.sql.Connection;

/**
 * Created by clouway on 6/6/14.
 */
@Singleton
public class PersistentBankDAO {
  @Inject
  public PersistentBankDAO(Provider<Connection> connectionProvider) {

  }
}
