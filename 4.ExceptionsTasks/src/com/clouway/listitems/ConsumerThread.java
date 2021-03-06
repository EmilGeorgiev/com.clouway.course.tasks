package com.clouway.listitems;

/**
 * Created by clouway on 1/15/14.
 */
public class ConsumerThread extends Thread {
  private final ListOperation list;

  public ConsumerThread(ListOperation list) {

    this.list = list;
  }

  @Override
  public void run() {
    list.remove();
    System.out.println(getName() + " finished");
  }
}
