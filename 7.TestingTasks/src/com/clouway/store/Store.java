package com.clouway.store;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by clouway on 12/21/13.
 */
public class Store {

  private List<Product> listOfProducts;

  public Store() {
    listOfProducts = new ArrayList<Product>();
  }

  /**
   * Adding products in the store.
   * @param product the type of products that are added.
   * @param amount a quantity that is added
   * @return how products are available in the store.
   */
  public int add(Product product, int amount) {
    if (!isAddNewProducts(product, amount)) {
      throw new FullStoreException("Store " + amount + "may hold only " + calculateEmptySpaceInStore(product));
    }
    addProductInStore(product);
    return product.getTempValue();
  }

  /**
   * Sell products from store.
   * @param product the type of products that are sold.
   * @param value a quantity that is sold.
   * @return how products are still available in the store.
   */
  public int sell(Product product, int value) {
    if (value > product.getTempValue()) {
      throw new NotSoAvailableException("Can be sold total: " + product.getTempValue());
    }
    return product.getTempValue() - value;
  }

  /**
   * Check whether you add new products.
   * @param product type of the products that will add.
   * @param amount a quantity that will add.
   * @return true if there is space in the store, and false if you do not.
   */
  private boolean isAddNewProducts(Product product, int amount) {
    int temValueAfterAdded = product.getTempValue() + amount;
    if (temValueAfterAdded <= product.getMaxValue()) {
      product.setTempValue(temValueAfterAdded);
      return  true;
    } else {
      return false;
    }
  }

  /**
   * Calculate how much empty space there is in the warehouse
   * @param product the type of products you wont to store in.
   * @return empty space in the store
   */
  private int calculateEmptySpaceInStore(Product product) {
    return (product.getMaxValue() - product.getTempValue());
  }

  /**
   * Adding new products of the store if this product still does not yet exist.
   * @param product the new product that add.
   */
  private void addProductInStore(Product product) {
    if (!listOfProducts.contains(product)) {
      listOfProducts.add(product);
    }
  }

  /**
   * Sort products by price.
   * @return
   */
  public List<Product> sortProductsByPrace() {
    Collections.sort(listOfProducts);
    return listOfProducts;
  }
}
