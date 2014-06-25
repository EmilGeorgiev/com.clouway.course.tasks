package com.clouway.http;

import com.clouway.core.BankAccountMessages;
import com.clouway.core.SiteMap;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Singleton
public class IncludePageController extends HttpServlet {

  private final SiteMap siteMap;
  private final BankAccountMessages bankAccountMessages;
  private Map<String, String> webPages = new HashMap<String, String>();

  @Inject
  public IncludePageController(SiteMap siteMap, BankAccountMessages bankAccountMessages) {
    this.siteMap = siteMap;
    this.bankAccountMessages = bankAccountMessages;
  }


  @Override
  public void init(ServletConfig config) throws ServletException {
    webPages.put(bankAccountMessages.deposit(), siteMap.depositPage());
    webPages.put(bankAccountMessages.viewAmount(), siteMap.viewAmountPage());
    webPages.put(bankAccountMessages.withdraw(), siteMap.withdrawingPage());
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doPost(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String pageName = req.getParameter("pageName");

    if((pageName != null) && webPages.containsKey(pageName)) {
      req.setAttribute(siteMap.contentPage(), webPages.get(pageName));

      RequestDispatcher requestDispatcher = req.getRequestDispatcher(siteMap.mainServlet());

      requestDispatcher.forward(req, resp);

      return;
    }

    resp.sendRedirect(siteMap.emptyPage());


  }

}
