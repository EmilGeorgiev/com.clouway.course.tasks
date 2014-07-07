package com.clouway.http;

import com.clouway.core.BuildPage;
import com.clouway.core.Page;
import com.clouway.core.SiteMap;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class NavigationPageController extends HttpServlet {
  private final SiteMap siteMap;
  private final BuildPage buildPage;

  @Inject
  public NavigationPageController(SiteMap siteMap, BuildPage buildPage) {

    this.siteMap = siteMap;
    this.buildPage = buildPage;

  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doPost(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    Page currentPage = buildPage.configure(req.getParameter(siteMap.requestPage()));

    req.setAttribute(siteMap.requestPage(), currentPage);

    RequestDispatcher dispatcher = req.getRequestDispatcher(siteMap.catalog());

    dispatcher.forward(req, resp);
  }
}
