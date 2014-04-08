package com.mypcompany;

import org.apache.log4j.Logger;

/**
 * Created by clouway on 4/7/14.
 */
public class MyClass {

  private static final Logger LOGGER = Logger.getLogger(MyClass.class.getName());

  public static void main(String[] args) {
    LOGGER.debug("hello I am debug");
  }
}
