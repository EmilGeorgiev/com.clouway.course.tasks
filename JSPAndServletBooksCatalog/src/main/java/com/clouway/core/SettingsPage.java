package com.clouway.core;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.List;

/**
 * Created by clouway on 7/3/14.
 */
@Singleton
public class SettingsPage implements BuildPage {

  private final BookRepository bookRepository;
  private final int lastPage;

  @Inject
  public SettingsPage(BookRepository bookRepository, Integer lastPage) {
    this.bookRepository = bookRepository;
    this.lastPage = lastPage;
  }

  @Override
  public Page configure(String requiredPage) {
    if (requiredPage == null || ("0".equals(requiredPage))) {
      requiredPage = "1";
    }
    int pageNumber = Integer.valueOf(requiredPage);
    if (pageNumber > lastPage) {
      pageNumber = lastPage;
    }

    List<Book> bookList = bookRepository.findAllBooksForPage(pageNumber);

    int nextPage = findNextPage(pageNumber);
    int previousPage = findPreviousPage(pageNumber);


    return PageBuilder.newPageDetails()
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
