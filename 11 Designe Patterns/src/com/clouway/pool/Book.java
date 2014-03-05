package com.clouway.pool;

/**
 * Created by clouway on 2/26/14.
 */
public class Book implements RestrictedObject {

  @Override
  public RestrictedObject createRestrictObject() {
    return new Book();
  }
}
