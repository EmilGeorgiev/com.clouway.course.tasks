package com.clouway.observer;

/**
 * Created by clouway on 2/27/14.
 */
public interface ProductOperationListener {
  void onProductRegistered(Product product);

  void onProductSold(Product product);

  //void printStatistics();
}
