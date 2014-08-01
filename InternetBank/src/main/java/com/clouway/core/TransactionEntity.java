package com.clouway.core;

import java.util.Date;

/**
 * @author Emil Georgiev <emogeorgiev88@gmail.com>
 */
public class TransactionEntity {

  public final String type;
  public final Double amount;
  public final Date date;

  public TransactionEntity(String type, Double amount, Date date) {
    this.type = type;
    this.amount = amount;
    this.date = date;
  }
}
