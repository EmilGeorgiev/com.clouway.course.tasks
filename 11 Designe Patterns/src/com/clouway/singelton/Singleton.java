package com.clouway.singelton;

/**
 * Created by clouway on 2/26/14.
 */
public class Singleton {
  private static Singleton ourInstance = new Singleton();

  public static Singleton getInstance() {
    return ourInstance;
  }

  private Singleton() {
    System.out.println("Create singleton class");
  }
}
