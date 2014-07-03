package com.clouway.core;

import java.util.List;

/**
 * Created by clouway on 7/3/14.
 */
public class PageDetailsBuilder {

  public static PageDetailsBuilder newPageDetails() {
    return new PageDetailsBuilder();
  }

  private int pageNumber;
  private int nextPage;
  private int previousPage;
  private List<Book> bookList;

  public PageDetailsBuilder pageNumber(int pageNumber) {
    this.pageNumber = pageNumber;
    return this;
  }

  public PageDetailsBuilder nextPage(int nextPage) {
    this.nextPage = nextPage;
    return this;
  }

  public PageDetailsBuilder previousPage(int previousPage) {
    this.previousPage = previousPage;
    return this;
  }

  public PageDetailsBuilder bookList(List<Book> bookList) {
    this.bookList = bookList;
    return this;
  }

  public PageDetails build() {
    return new PageDetails(pageNumber, nextPage, previousPage, bookList);
  }


}
