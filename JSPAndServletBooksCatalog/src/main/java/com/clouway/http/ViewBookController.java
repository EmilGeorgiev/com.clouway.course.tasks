package com.clouway.http;

import com.clouway.core.Book;
import com.clouway.core.BookId;
import com.clouway.core.Configured;
import com.clouway.core.SiteMap;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by clouway on 7/4/14.
 */
@Singleton
public class ViewBookController extends HttpServlet {

  private final SiteMap siteMap;
  private final Configured<Book> configured;
  private final Provider<BookId> idProvider;

  @Inject
  public ViewBookController(SiteMap siteMap, Configured<Book> configured, Provider<BookId> idProvider) {

    this.siteMap = siteMap;
    this.configured = configured;
    this.idProvider = idProvider;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doPost(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    Book bookDetails;

    if (idProvider.get().getId() != null) {
      bookDetails = configured.configure(idProvider.get().getId());
    } else {
      bookDetails = configured.configure(req.getParameter(siteMap.bookId()));
    }

    req.setAttribute(siteMap.details(), bookDetails);

    RequestDispatcher dispatcher = req.getRequestDispatcher(siteMap.bookDetails());

    dispatcher.forward(req, resp);
  }
}
