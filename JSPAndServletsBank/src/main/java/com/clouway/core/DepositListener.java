package com.clouway.core;

/**
 * Created by clouway on 6/12/14.
 */
public interface DepositListener {

  void onTransaction(float amount, int userID, String deposit);
}
