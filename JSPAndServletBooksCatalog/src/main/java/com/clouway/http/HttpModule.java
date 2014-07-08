package com.clouway.http;

import com.clouway.core.BookId;
import com.clouway.core.BookRepository;
import com.clouway.core.BuildPage;
import com.clouway.core.PostRepository;
import com.clouway.core.SettingsPage;
import com.clouway.core.SiteMap;
import com.clouway.persistent.PersistentBookRepository;
import com.google.inject.Provider;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.servlet.RequestScoped;
import com.google.inject.servlet.ServletModule;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;

/**
 * Created by clouway on 6/30/14.
 */
public class HttpModule extends ServletModule {

  @Override
  protected void configureServlets() {
    filter("/*").through(ConnectionPerRequestFilter.class);

    serve("/navigationController").with(NavigationPageController.class);
    serve("/viewBookController").with(ViewBookController.class);
    serve("/postController").with(PostController.class);


  }

  @Provides
  public PostRepository getPostRepository(Provider<Connection> connectionProvider) {
    return new PersistentBookRepository(connectionProvider, bookOnPage(3));
  }

  @Provides
  public BookRepository providesBookRepository(Provider<Connection> connectionProvider) {
    return new PersistentBookRepository(connectionProvider, bookOnPage(3));
  }

  @Provides
  @Singleton
  public BuildPage providesBuildPage(BookRepository bookRepository) {
    return new SettingsPage(bookRepository, lastPage(5));
  }

  @Provides
  @RequestScoped
  public BookId provideBookId(Provider<HttpServletRequest> requestProvides) {
    Cookie[] cookies = requestProvides.get().getCookies();

    for (Cookie cookie : cookies) {
      if("bookId".equals(cookie.getName())) {
        return new BookId(cookie.getValue());
      }
    }
    return new BookId(null);
  }

  @Provides
  public Connection getConnection() {
    return ConnectionPerRequestFilter.CONNECTION.get();
  }

  @Provides
  public SiteMap getSiteMap() {
    return new SiteMap() {
      @Override
      public String catalog() {
        return "catalog.jsp";
      }

      @Override
      public String page() {
        return null;
      }

      @Override
      public String requestPage() {
        return "requestPage";
      }

      @Override
      public String bookId() {
        return "bookId";
      }

      @Override
      public String bookDetails() {
        return "bookDetails.jsp";
      }

      @Override
      public String details() {
        return "details";
      }

      @Override
      public String author() {
        return "author";
      }

      @Override
      public String postContent() {
        return "postContent";
      }

      @Override
      public String viewBookController() {
        return "/viewBookController";
      }

      @Override
      public String lastPage() {
        return "lastPage";
      }
    };
  }

  private int lastPage(int lastPage) {
    return lastPage;
  }

  private int bookOnPage(int bookCount) {
    return bookCount;
  }
}
