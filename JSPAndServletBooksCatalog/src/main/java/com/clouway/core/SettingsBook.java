package com.clouway.core;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Setting book details.
 */
@Singleton
public class SettingsBook implements Configured<Book> {

  private final BookRepository bookRepository;

  @Inject
  public SettingsBook(BookRepository bookRepository) {

    this.bookRepository = bookRepository;

  }

  @Override
  public Book configure(String id) {

    int bookId = Integer.parseInt(id);

    return bookRepository.findBookById(bookId);
  }
}
