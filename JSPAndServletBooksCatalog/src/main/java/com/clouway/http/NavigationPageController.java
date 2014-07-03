package com.clouway.http;

import com.clouway.core.PageConfigure;
import com.clouway.core.PageDetails;
import com.clouway.core.SiteMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class NavigationPageController extends HttpServlet {
  private final SiteMap siteMap;
  private final PageConfigure pageConfigure;

  public NavigationPageController(SiteMap siteMap, PageConfigure pageConfigure) {

    this.siteMap = siteMap;
    this.pageConfigure = pageConfigure;

  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doPost(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    PageDetails details = pageConfigure.configurePage(req.getParameter(siteMap.requestPage()));

    req.setAttribute(siteMap.requestPage(), details);

    RequestDispatcher dispatcher = req.getRequestDispatcher(siteMap.catalog());

    dispatcher.forward(req, resp);
  }
}
