package com.clouway.persistent;

import com.clouway.core.Book;
import com.clouway.core.BookRepository;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.clouway.core.BookBuilder.newBook;


@Singleton
public class PersistentBookRepository implements BookRepository {


  private final Provider<Connection> connectionProvider;

  @Inject
  public PersistentBookRepository(Provider<Connection> connectionProvider) {
    this.connectionProvider = connectionProvider;
  }

  @Override
  public List<Book> findAllBooksForPage(int pageNumber) {
    List<Book> bookList = new ArrayList<Book>();

    PreparedStatement preparedStatement = null;
    Connection connection = connectionProvider.get();

    try {
      preparedStatement = connection.prepareStatement("SELECT title, publishers, year_publisher, belongs_page FROM Books " +
                                                      "WHERE belongs_page = ?");

      preparedStatement.setInt(1, pageNumber);

      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        String title = resultSet.getString("title");
        String publishers = resultSet.getString("publishers");
        int yearPublisher = resultSet.getInt("year_publisher");
        int belongsPage = resultSet.getInt("belongs_page");

        bookList.add(newBook()
                              .title(title)
                              .publishers(publishers)
                              .publisherYear(yearPublisher)
                              .belongsPage(belongsPage)
                              .build());
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return bookList;
  }

}
