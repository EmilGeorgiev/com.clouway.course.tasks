package com.clouway.core;

/**
 *
 */
public interface BankRepository {

  /**
   * Increment or decrement account on user.
   * @param transaction contains information about transaction
   * @return Result who contains message whether transaction is success or not.
   * Also contains current amount on user after update.
   */
  Result updateBalance(Transaction transaction);

}
