package com.clouway.http;

import com.clouway.core.BookRepository;
import com.clouway.core.Configured;
import com.clouway.core.SettingsPage;
import com.clouway.core.SiteMap;
import com.clouway.persistent.PersistentBookRepository;
import com.google.inject.Provides;
import com.google.inject.servlet.ServletModule;

import java.sql.Connection;

/**
 * Created by clouway on 6/30/14.
 */
public class HttpModule extends ServletModule {

  @Override
  protected void configureServlets() {
    filter("/*").through(ConnectionPerRequestFilter.class);

    serve("/navigationController").with(NavigationPageController.class);

    bind(BookRepository.class).to(PersistentBookRepository.class);
    bind(Configured.class).to(SettingsPage.class);
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
    };
  }
}
