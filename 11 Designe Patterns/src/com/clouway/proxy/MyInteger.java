package com.clouway.proxy;

/**
 * Created by clouway on 2/27/14.
 */
public class MyInteger implements IntegerFactory {
  @Override
  public Integer createInstance(int number) {
    return new Integer(number);
  }
}
