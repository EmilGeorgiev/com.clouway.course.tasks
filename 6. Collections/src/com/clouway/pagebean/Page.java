package com.clouway.pagebean;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: clouway
 * Date: 12/12/13
 * Time: 5:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class Page {
  private final List<String> page;
  private final int pageNumber;

  public Page(List<String> page, int numberOfPage) {
    this.page = page;
    this.pageNumber = numberOfPage;
  }

  /**
   * Print content of page of the console.
   */
  public void printPage() {
    System.out.print("page: " + pageNumber + ": ");
    for (String word : page) {
      System.out.print(word + " ");
    }
  }
}