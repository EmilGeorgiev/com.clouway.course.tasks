package com.clouway.core;

import java.util.List;

/**
 * Created by clouway on 7/3/14.
 */
public class PageBuilder {

  public static PageBuilder newPageDetails() {
    return new PageBuilder();
  }

  private int pageNumber;
  private int nextPage;
  private int previousPage;
  private List<Book> bookList;

  public PageBuilder pageNumber(int pageNumber) {
    this.pageNumber = pageNumber;
    return this;
  }

  public PageBuilder nextPage(int nextPage) {
    this.nextPage = nextPage;
    return this;
  }

  public PageBuilder previousPage(int previousPage) {
    this.previousPage = previousPage;
    return this;
  }

  public PageBuilder bookList(List<Book> bookList) {
    this.bookList = bookList;
    return this;
  }

  public Page build() {
    return new Page(pageNumber, nextPage, previousPage, bookList);
  }


}
