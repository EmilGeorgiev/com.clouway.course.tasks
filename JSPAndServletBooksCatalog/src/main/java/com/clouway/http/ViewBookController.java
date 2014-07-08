package com.clouway.http;

import com.clouway.core.Book;
import com.clouway.core.BookId;
import com.clouway.core.BookRepository;
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
  private final BookRepository bookRepository;
  private final Provider<BookId> idProvider;

  @Inject
  public ViewBookController(SiteMap siteMap, BookRepository bookReapository, Provider<BookId> idProvider) {

    this.siteMap = siteMap;
    this.bookRepository = bookReapository;
    this.idProvider = idProvider;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doPost(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    Book bookDetails;
    String id = siteMap.bookId();

    if (req.getParameter(id) != null) {
      int bookId = Integer.parseInt(req.getParameter(id));
      bookDetails = bookRepository.findBookById(bookId);
    } else {
      int bookId = Integer.parseInt(idProvider.get().getId());
      bookDetails = bookRepository.findBookById(bookId);
    }

    req.setAttribute(siteMap.details(), bookDetails);

    RequestDispatcher dispatcher = req.getRequestDispatcher(siteMap.bookDetails());

    dispatcher.forward(req, resp);
  }
}
