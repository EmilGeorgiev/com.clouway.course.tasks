package com.clouway.http;

import com.clouway.core.BookDetails;
import com.clouway.core.Configured;
import com.clouway.core.SiteMap;
import com.google.inject.Inject;
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
  private final Configured<BookDetails> configured;

  @Inject
  public ViewBookController(SiteMap siteMap, Configured<BookDetails> configured) {

    this.siteMap = siteMap;
    this.configured = configured;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doPost(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    BookDetails bookDetails = configured.configure(req.getParameter(siteMap.bookId()));

    req.setAttribute(siteMap.details(), bookDetails);

    RequestDispatcher dispatcher = req.getRequestDispatcher(siteMap.bookDetails());

    dispatcher.forward(req, resp);
  }
}
