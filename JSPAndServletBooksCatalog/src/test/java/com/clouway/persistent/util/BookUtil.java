package com.clouway.persistent.util;

import com.clouway.core.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by clouway on 7/3/14.
 */
public class BookUtil {
  private final Connection connection;

  public BookUtil(Connection connection) {

    this.connection = connection;
  }

  public void addBook(Book book) {
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = connection.prepareStatement("INSERT INTO Books (title, publishers, year_publisher, belongs_page) " +
              "VALUE (?, ?, ?, ?)");

      preparedStatement.setString(1, book.getTitle());
      preparedStatement.setString(2, book.getPublishers());
      preparedStatement.setInt(3, book.getYearPublisher());
      preparedStatement.setInt(4, book.getBelongsPage());

      preparedStatement.executeUpdate();

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
  }
}
