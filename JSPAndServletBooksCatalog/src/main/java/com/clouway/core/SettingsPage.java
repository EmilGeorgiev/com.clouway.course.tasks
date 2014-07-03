package com.clouway.core;

import java.util.List;

/**
 * Created by clouway on 7/3/14.
 */
public class SettingsPage implements PageConfigure {

  private final BookRepository bookRepository;

  public SettingsPage(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @Override
  public PageDetails configurePage(String requiredPage) {
    int pageNumber = Integer.valueOf(requiredPage);
    List<Book> bookList = bookRepository.findAllBooksForPage(pageNumber);

    int nextPage = findNextPage(pageNumber);
    int previousPage = findPreviousPage(pageNumber);


    return PageDetailsBuilder.newPageDetails()
                              .bookList(bookList)
                              .nextPage(nextPage)
                              .previousPage(previousPage)
                              .pageNumber(pageNumber)
                              .build();
  }

  private int findPreviousPage(int pageNumber) {
    int previousPage = pageNumber - 1;

    if (previousPage < 0) {
      return 1;
    }

    return previousPage;
  }

  private int findNextPage(int pageNumber) {

    return pageNumber + 1;
  }
}
