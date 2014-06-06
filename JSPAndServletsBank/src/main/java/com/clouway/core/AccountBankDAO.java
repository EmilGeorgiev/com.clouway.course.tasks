package com.clouway.core;

/**
 * Created by clouway on 5/28/14.
 */
public interface AccountBankDAO {

  void deposit(int amount, int userID);

  int getCurrentUserBankAmount();

  int withdrawing(int withdrawingAmount, int userID);

}
