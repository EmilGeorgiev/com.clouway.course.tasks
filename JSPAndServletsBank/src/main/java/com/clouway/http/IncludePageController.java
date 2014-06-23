package com.clouway.http;

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
 * Created by clouway on 6/23/14.
 */
@Singleton
public class IncludePageController extends HttpServlet {

  private final SiteMap siteMap;

  @Inject
  public IncludePageController(SiteMap siteMap) {
    this.siteMap = siteMap;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String pageName = req.getParameter("pageName");

    req.setAttribute(siteMap.contentPage(), pageName);

    RequestDispatcher requestDispatcher = req.getRequestDispatcher(siteMap.mainPage());

    requestDispatcher.forward(req, resp);
  }
}
