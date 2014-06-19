package com.clouway.persistents.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by clouway on 6/19/14.
 */
public class BankUtil {

  private Map<Integer, Float> bankAmount = new HashMap<Integer, Float>();


  public void deposit(float depositAmount, int userID) {
    bankAmount.put(userID, depositAmount);
  }

  public void withdraw(float withdrawAmount, int userId) {
    float amount = bankAmount.get(userId);

    amount -= withdrawAmount;

    bankAmount.put(userId, amount);
  }
}
