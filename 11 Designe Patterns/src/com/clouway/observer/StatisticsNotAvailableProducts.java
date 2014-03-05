package com.clouway.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Keep statistic with sold products(those which are no longer available)
 * Created by Emil Georgiev on 2/27/14.
 */
public class StatisticsNotAvailableProducts implements ProductOperationListener {

  private List<Product> listSoldProducts = new ArrayList<Product>();


  /**
   * Remove product from statistic if it is available.
   * @param product which is removed.
   */
  @Override
  public void onProductRegistered(Product product) {
    if (listSoldProducts.contains(product)) {
      listSoldProducts.remove(product);
    }
  }


  /**
   * Insert new product which is sold.
   * @param product which is sold
   */
  @Override
  public void onProductSold(Product product) {
    listSoldProducts.add(product);
  }

  public List<Product> getListSoldProducts() {
    return listSoldProducts;
  }
}
