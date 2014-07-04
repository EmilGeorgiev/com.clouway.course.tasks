package com.clouway.core;

/**
 * Created by clouway on 7/2/14.
 */
public interface Configured<T> {
  T configure(String requiredObject);
}
