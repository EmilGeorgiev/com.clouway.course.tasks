package com.clouway.core;

/**
 * Created by clouway on 6/10/14.
 */
public interface CurrentAmountRepository {

  float getCurrentUserBankAmount(int userID);
}
