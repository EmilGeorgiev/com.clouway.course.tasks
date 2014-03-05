package com.clouway.observer;


import java.util.ArrayList;
import java.util.List;

/**
 * Keep statistics for available products in store.
 * Created by Emil Georgiev on 2/27/14.
 */
public class StatisticAvailableProducts implements ProductOperationListener {

  private List<Product> listAvailableProducts = new ArrayList<Product>();

  public List<Product> getListAvailableProducts() {
    return listAvailableProducts;
  }

  /**
   * Update statistics by insert new product in list.
   * @param product which inserted
   */
  @Override
  public void onProductRegistered(Product product) {
    listAvailableProducts.add(product);
  }

  /**
   * Update statistics by removing product if it is available in store.
   * @param product which removing.
   */
  @Override
  public void onProductSold(Product product) {
    if (listAvailableProducts.contains(product)) {
      listAvailableProducts.remove(product);
    }
  }

}
