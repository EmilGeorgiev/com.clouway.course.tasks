package com.clouway.store;

/**
 * Created by clouway on 12/21/13.
 */
public class Product implements Comparable<Product>{
  private int tempValue;
  private int maxValue;
  private Double price;

  public Product(int maxValue, double price) {
    this.tempValue = 0;
    this.maxValue = maxValue;
    this.price = price;
  }

  public int getTempValue() {
    return tempValue;
  }

  public int getMaxValue() {
    return maxValue;
  }

  public void setTempValue(int tempValue) {
    this.tempValue = tempValue;
  }

  @Override
  public int compareTo(Product o) {
    return this.price.compareTo(o.price);
  }
}
