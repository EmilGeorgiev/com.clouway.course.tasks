package com.clouway.persistents.util;

import com.clouway.core.AccountBankDAO;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by clouway on 6/19/14.
 */
public class BankUtil implements AccountBankDAO {

  private Map<Integer, Float> bankAccounts = new HashMap<Integer, Float>();

  @Override
  public void deposit(float depositAmount, int userID) {
    bankAccounts.put(userID, depositAmount);
  }

  @Override
  public Float withdraw(float withdrawAmount, int userID) {
    float amount = bankAccounts.get(userID);

    amount -= withdrawAmount;

    bankAccounts.put(userID, amount);
    return withdrawAmount;
  }

  @Override
  public Float getCurrentUserBankAmount(int userID) {

    return bankAccounts.get(userID);
  }
}
