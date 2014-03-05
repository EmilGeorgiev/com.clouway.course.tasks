package com.clouway.proxy;

/**
 * Created by clouway on 2/27/14.
 */
public class IntegerProxy implements IntegerFactory{

  private IntegerFactory integerFactory;

  public IntegerProxy(IntegerFactory integerFactory) {
    this.integerFactory = integerFactory;
  }

  @Override
  public Integer createInstance(int number) {
    Integer integer = integerFactory.createInstance(number);
    return integer;
  }
}
