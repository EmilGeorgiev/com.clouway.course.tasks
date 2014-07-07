package com.clouway.persistent;

import com.clouway.core.Book;
import com.clouway.core.BookBuilder;
import com.clouway.core.BookRepository;
import com.clouway.core.Post;
import com.clouway.core.PostRepository;
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
public class PersistentBookRepository implements BookRepository, PostRepository {


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
      preparedStatement = connection.prepareStatement("SELECT id_book, title, publishers, year_publisher, description, belongs_page " +
                                                      "FROM Catalog_Books WHERE belongs_page = ?");

      preparedStatement.setInt(1, pageNumber);

      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        int bookId = resultSet.getInt("id_book");
        String title = resultSet.getString("title");
        String publishers = resultSet.getString("publishers");
        int yearPublisher = resultSet.getInt("year_publisher");
        String description = resultSet.getString("description");
        int belongsPage = resultSet.getInt("belongs_page");

        bookList.add(newBook()
                .id(bookId)
                .title(title)
                .publishers(publishers)
                .publisherYear(yearPublisher)
                .belongsPage(belongsPage)
                .description(description)
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

  @Override
  public Book findBookById(int bookId) {

    PreparedStatement preparedStatement = null;

    Connection connection = connectionProvider.get();

    try {
      preparedStatement = connection.prepareStatement("SELECT title, publishers, year_publisher, description, belongs_page FROM Catalog_Books " +
              "WHERE id_book = ?");

      preparedStatement.setInt(1, bookId);

      ResultSet resultSet = preparedStatement.executeQuery();

      resultSet.next();

      String title = resultSet.getString("title");
      String publishers = resultSet.getString("publishers");
      String description = resultSet.getString("description");
      int yearPublisher = resultSet.getInt("year_publisher");
      int belongsPage = resultSet.getInt("belongs_page");

      List<Post> listPost = findPostByBookId(bookId);

      return BookBuilder.newBook().description(description)
                                  .belongsPage(belongsPage)
                                  .id(bookId)
                                  .publishers(publishers)
                                  .publisherYear(yearPublisher)
                                  .title(title)
                                  .postList(listPost)
                                  .build();

    } catch (SQLException e) {
      e.printStackTrace();
    }finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return null;
  }




  @Override
  public void addPost(Post post) {
    PreparedStatement preparedStatement = null;

    Connection connection = connectionProvider.get();

    try {
      preparedStatement = connection.prepareStatement("INSERT INTO Posts (author, post, id_book) " +
              "VALUES (?, ?, ?)");

      preparedStatement.setString(1, post.getAuthor());
      preparedStatement.setString(2, post.getPostContent());
      preparedStatement.setInt(3, post.getBookId());

      preparedStatement.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    }finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  private List<Post> findPostByBookId(int bookId) {

    List<Post> postList = new ArrayList<Post>();

    PreparedStatement preparedStatement = null;

    Connection connection = connectionProvider.get();

    try {
      preparedStatement = connection.prepareStatement("SELECT author, post, id_book FROM Posts " +
              "WHERE id_book = ?");

      preparedStatement.setInt(1, bookId);

      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {

        String author = resultSet.getString("author");

        String post = resultSet.getString("post");

        postList.add(new Post(author, post, bookId));

      }

    } catch (SQLException e) {
      e.printStackTrace();
    }finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return postList;
  }
}
