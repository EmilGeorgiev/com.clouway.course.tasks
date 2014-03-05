package com.clouway.observer;

/**
 * Created by clouway on 2/27/14.
 */
public class DemoObserver {
  public static void main(String[] args) {
    StatisticAvailableProducts statisticsAvailable = new StatisticAvailableProducts();
    StatisticsNotAvailableProducts statisticsSold = new StatisticsNotAvailableProducts();

    Warehouse warehouse = new Warehouse();
    warehouse.addListener(statisticsAvailable);
    warehouse.addListener(statisticsSold);

    Apple apple = new Apple();
    Oranges oranges = new Oranges();

    warehouse.registerProduct(apple);
    warehouse.registerProduct(oranges);
    System.out.println("In moment in store has: ");
    for (Product product : statisticsAvailable.getListAvailableProducts()) {
      product.printName();
    }

    warehouse.saleProduct(apple);
    System.out.println("In moment not available: ");
    for (Product product : statisticsSold.getListSoldProducts()) {
      product.printName();
    }



  }
}
