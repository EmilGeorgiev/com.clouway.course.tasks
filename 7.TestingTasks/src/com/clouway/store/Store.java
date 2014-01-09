package com.clouway.store;

import java.util.ArrayList;
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
   *
   * @param product  the type of products that are added.
   * @param quantity a quantity that is added
   * @return how products are available in the store.
   */
  public int add(Product product, int quantity) {
    areAddNewProducts(product, quantity);
    addProductInStore(product);
    return product.getCurrentValue();
  }

  /**
   * Sell products from store.
   *
   * @param product  the type of products that are sold.
   * @param quantity a quantity that is sold.
   * @return how products are still available in the store.
   */
  public int sell(Product product, int quantity) {
    if (quantity > product.getCurrentValue()) {
      throw new NotEnoughProductsException(String.format("Can be sold total: %d" + product.getCurrentValue()));
    }
    return product.getCurrentValue() - quantity;
  }

  /**
   * Check whether you add new products.
   *
   * @param product  type of the products that will add.
   * @param quantity a quantity that will add.
   * @return true if there is space in the store, and false if you do not.
   */
  private void areAddNewProducts(Product product, int quantity) {
    int temValueAfterAdded = product.getCurrentValue() + quantity;
    if (temValueAfterAdded > product.getMaxValue()) {
      throw new FullStoreException(String.format("Can't add %d items in the store only %d available space",
              quantity, calculateEmptySpaceInStore(product)));
    }
    product.setCurrentValue(temValueAfterAdded);
  }

  /**
   * Calculate how much empty space there is in the warehouse
   *
   * @param product the type of products you wont to store in.
   * @return empty space in the store
   */
  private int calculateEmptySpaceInStore(Product product) {
    return (product.getMaxValue() - product.getCurrentValue());
  }

  /**
   * Adding new products of the store if this product still does not yet exist.
   *
   * @param product the new product that add.
   */
  private void addProductInStore(Product product) {
    listOfProducts.add(product);
  }

  /**
   * Sort products by price.
   *
   * @return
   */
  public List<Product> sortProductsByPrice() {
    Collections.sort(listOfProducts);
    return listOfProducts;
  }
}
