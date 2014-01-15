package com.clouway.listitems;

/**
 * Created by clouway on 1/15/14.
 */
public class ProducerThread extends Thread {
  private final ListOperation list;
  private final String symbol;

  public ProducerThread(ListOperation list, String symbol) {

    this.list = list;
    this.symbol = symbol;
  }

  @Override
  public void run() {
    int count = 0;
    while (count != 6) {
      list.add(symbol);
      count++;
    }
  }
}
