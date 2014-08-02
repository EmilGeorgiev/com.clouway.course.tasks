package com.clouway.core;

/**
 *
 */
public interface BankRepository {

  /**
   * Increment account on user with same value.
   *
   * @return Result who contains message whether transaction is success or not.
   * Also contains current amount on user after update.
   */
  TransactionInfo deposit(Double amount);


  /**
   * Decrement account on user with same value.
   *
   * @return Result who contains message whether transaction is success or not.
   * Also contains current amount on user after update.
   */
  TransactionInfo withdraw(Double amount);


    /**
     * Retrieve current amount on user.
      * @return amount on user.
     */
  Double getCurrentAmount();
}
