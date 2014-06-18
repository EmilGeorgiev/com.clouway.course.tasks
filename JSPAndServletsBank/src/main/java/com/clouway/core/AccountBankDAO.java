package com.clouway.core;

/**
 * Created by clouway on 5/28/14.
 */
public interface AccountBankDAO {

  void deposit(float amount, int userID);

  float withdraw(float withdrawingAmount, int userID);

}
