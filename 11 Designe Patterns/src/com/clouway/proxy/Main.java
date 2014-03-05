package com.clouway.proxy;

/**
 * Created by clouway on 2/27/14.
 */
public class Main {
  public static void main(String[] args) {
    IntegerFactory integerFactory = new MyInteger();

    IntegerProxy integerProxy = new IntegerProxy(integerFactory);

    Client client = new Client(integerProxy);

//    System.out.println(client.representInByte(5));
  }
}
