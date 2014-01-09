package com.clouway.store;

/**
 * Created by clouway on 12/21/13.
 */
public class Product implements Comparable<Product>{
  private int currentValue;
  private int maxValue;
  private Double price;

  public Product(int maxValue, double price) {
    this.currentValue = 0;
    this.maxValue = maxValue;
    this.price = price;
  }

  public int getCurrentValue() {
    return currentValue;
  }

  public int getMaxValue() {
    return maxValue;
  }

  public void setCurrentValue(int currentValue) {
    this.currentValue = currentValue;
  }

  @Override
  public int compareTo(Product o) {
    return this.price.compareTo(o.price);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Product product = (Product) o;

    if (maxValue != product.maxValue) return false;
    if (price != null ? !price.equals(product.price) : product.price != null) return false;

    return true;
  }


}
