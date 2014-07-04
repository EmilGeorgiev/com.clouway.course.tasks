package com.clouway.persistent;

import com.clouway.core.Book;
import com.clouway.persistent.util.BookUtil;
import com.google.inject.util.Providers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PersistentBookRepositoryTest {

  private PersistentBookRepository persistentBookRepository;

  private Book javaSE;
  private Book javaEE;

  private BookUtil bookUtil;
  @Rule
  public DatastoreRule datastoreRule = new DatastoreRule();

  @Before
  public void setUp() throws SQLException {

    Connection connection = datastoreRule.getDataSource().getConnection();

    persistentBookRepository = new PersistentBookRepository(Providers.of(connection));

    javaSE = new Book();
    javaEE = new Book();

    bookUtil = new BookUtil(connection);

  }

  @Test
  public void findAllBookForSamePage() throws Exception {

    pretendThatOnPageHas(bookList(javaEE, javaSE), pageNumber(1));

    List<Book> bookList = persistentBookRepository.findAllBooksForPage(1);

    assertThat(bookList, is(Arrays.asList(javaEE, javaSE)));

  }


  @Test
  public void testName() throws Exception {


  }

  private void pretendThatOnPageHas(List<Book> books, int pageNumber) {
    for(Book book : books) {
      book.setBelongsPage(pageNumber);
      bookUtil.addBook(book);
    }
  }

  private List<Book> bookList(Book book1, Book book2) {

    List<Book> bookList = new ArrayList<Book>();

    bookList.add(book1);

    bookList.add(book2);

    return bookList;
  }

  private int pageNumber(int pageNumber) {
    return pageNumber;
  }
}
