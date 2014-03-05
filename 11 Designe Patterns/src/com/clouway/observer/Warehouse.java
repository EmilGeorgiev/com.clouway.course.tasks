package com.clouway.observer;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by clouway on 2/27/14.
 */
public class Warehouse {
  private List<ProductOperationListener> productOperationListenerList = new ArrayList<ProductOperationListener>();

  /**
   * Notify classes responsible for statistics, that have been made sold  of a product.
   * @param product which is sold.
   */
  public void saleProduct(Product product) {
    notifySale(product);
  }

  /**
   * Notify classes responsible for statistics, that have been made inserted of a product
   * @param product which is inserted in store.
   */
  public void registerProduct(Product product) {
    notifyRegistration(product);
  }


  public void addListener(ProductOperationListener productOperationListener) {
    productOperationListenerList.add(productOperationListener);
  }

  private void notifyRegistration(Product product) {
    for (ProductOperationListener productOperationListener : productOperationListenerList) {
      productOperationListener.onProductRegistered(product);
    }
  }

  private void notifySale(Product product) {
    for (ProductOperationListener productOperationListener : productOperationListenerList) {
      productOperationListener.onProductSold(product);
    }
  }

}
