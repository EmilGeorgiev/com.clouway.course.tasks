package com.clouway.core;

/**
 * Contains result of executed transaction.
 */
public class TransactionInfo {

  private final String message;

  public TransactionInfo(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

}
