package com.clouway.http;

import com.clouway.core.AuthorizationURLParameters;
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
public class IncludePageController extends HttpServlet {

  private final SiteMap siteMap;
  private final AuthorizationURLParameters authorizationURLParameters;

  @Inject
  public IncludePageController(SiteMap siteMap, AuthorizationURLParameters authorizationURLParameters) {
    this.siteMap = siteMap;
    this.authorizationURLParameters = authorizationURLParameters;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doPost(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String urlParameter = req.getParameter("pageName");

    String pageName = authorizationURLParameters.getWebPageIfParameterIsAuthorize(urlParameter);

    if(pageName != null) {
      req.setAttribute(siteMap.contentPage(), pageName);

      RequestDispatcher requestDispatcher = req.getRequestDispatcher(siteMap.mainServlet());

      requestDispatcher.forward(req, resp);

      return;
    }

    resp.sendError(404, (siteMap.errorNotFound() + urlParameter));

  }

}
