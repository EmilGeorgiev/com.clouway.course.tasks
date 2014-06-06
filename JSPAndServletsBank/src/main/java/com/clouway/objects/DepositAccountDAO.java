package com.clouway.objects;

/**
 * Created by clouway on 5/28/14.
 */
public interface DepositAccountDAO {

  void deposit(int amount, int userID);

  int getCurrentUserBankAmount();

  int withdrawing(int withdrawingAmount, int userID);

}
