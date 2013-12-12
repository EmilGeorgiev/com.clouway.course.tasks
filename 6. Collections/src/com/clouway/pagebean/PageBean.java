package com.clouway.pagebean;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: clouway
 * Date: 12/10/13
 * Time: 11:12 AM
 * To change this template use File | Settings | File Templates.
 */
public class PageBean {

  private final int PAGE_SIZE = 3;
  private List<String> listOfPages;
  private List<String> page;
  private int fromIndex = 0;
  private int toIndex = 0;
  private int numberOfPage = 0;

  public PageBean(List<String> listOfPages) {
    this.listOfPages = listOfPages;
    this.fromIndex -= PAGE_SIZE;
  }

  /**
   * Go to the next page.If there is no next page display error message.
   */
  public Page next() {
    if (toIndex < listOfPages.size()) {
      fromIndex += PAGE_SIZE;
      toIndex += PAGE_SIZE;
      numberOfPage++;
      page = listOfPages.subList(fromIndex, maxSize());
    } else {
      throw new IllegalStateException("No next page");
    }
    return new Page(page, numberOfPage);
  }

  /**
   * Go to the previous page.If there is no previous page display message.
   */
  public Page previous() {
    if (toIndex != PAGE_SIZE) {
      fromIndex -= PAGE_SIZE;
      toIndex -= PAGE_SIZE;
      numberOfPage--;
      page = listOfPages.subList(fromIndex, maxSize());
    } else {
      throw new IllegalStateException("No previous page");
    }
    return new Page(page, numberOfPage);
  }

  /**
   * Check whether the following elements.
   *
   * @return Returns true if the following elements, or false if there is no.
   */
  public boolean hasNext() {
    return (toIndex == listOfPages.size()) ? false : true;
  }

  /**
   * Check previous pages have.
   *
   * @return Returns true if the previous pages, or false if there is no.
   */
  public boolean hasPrevious() {
    return toIndex == PAGE_SIZE ? false : true;
  }

  /**
   * Go to the the first page and make it current.
   */
  public Page firstPage() {
    fromIndex = 0;
    toIndex = PAGE_SIZE;
    numberOfPage = 1;
    page = listOfPages.subList(fromIndex, toIndex);
    return new Page(page, numberOfPage);
  }

  /**
   * Returns the last page and makes it current
   */
  public Page lastPage() {
    fromIndex = listOfPages.size() - PAGE_SIZE;
    toIndex = listOfPages.size();
    numberOfPage = (listOfPages.size() / PAGE_SIZE);
    page = listOfPages.subList(fromIndex, toIndex);
    return new Page(page, numberOfPage);
  }

  /**
   * Show number of the current page.
   *
   * @return the current page numbers.
   */
  public int getCurrentPageNumber() {
    return numberOfPage;
  }

  /**
   * Checked whether toIndex is greater than the size of the list
   *
   * @return toIndex if it less than size og the list or return size of the list.
   */
  private int maxSize() {
    return toIndex > listOfPages.size() ? listOfPages.size() : toIndex;
  }
}
