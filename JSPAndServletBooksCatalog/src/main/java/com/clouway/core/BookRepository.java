package com.clouway.core;

import java.util.List;

/**
 * Created by clouway on 7/3/14.
 */
public interface BookRepository {
  List<Book> findAllBooksForPage(int pageNumber);
}
