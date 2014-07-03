package com.clouway.core;

import java.util.List;

/**
 * Created by clouway on 7/3/14.
 */
public class PageDetails {

  private int pageNumber;
  private int nextPage;
  private int previousPage;
  private List<Book> bookList;

  public PageDetails(int pageNumber, int nextPage, int previousPage, List<Book> bookList) {
    this.pageNumber = pageNumber;
    this.nextPage = nextPage;
    this.previousPage = previousPage;
    this.bookList = bookList;
  }

  public int getPageNumber() {
    return pageNumber;
  }

  public int getNextPage() {
    return nextPage;
  }

  public int getPreviousPage() {
    return previousPage;
  }

  public List<Book> getBookList() {
    return bookList;
  }
}
