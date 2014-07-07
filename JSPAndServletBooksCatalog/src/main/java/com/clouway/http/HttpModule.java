package com.clouway.http;

import com.clouway.core.*;
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

    bind(BookRepository.class).to(PersistentBookRepository.class);
    bind(PostRepository.class).to(PersistentBookRepository.class);

  }

  @Provides
  @Singleton
  public Configured<Page> provideConfiguredPage(BookRepository bookRepository) {
    return new SettingsPage(bookRepository);
  }

  @Provides
  @Singleton
  public Configured<Book> provideConfiguredBook(BookRepository bookRepository) {
    return new SettingsBook(bookRepository);
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
      public String books() {
        return null;
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
      public String bookInfoPage() {
        return "bookInfoPage.jsp";
      }

      @Override
      public String viewBookController() {
        return "/viewBookController";
      }
    };
  }
}
