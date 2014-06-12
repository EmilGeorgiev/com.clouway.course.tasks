package com.clouway.http;

import com.clouway.core.CurrentUser;
import com.clouway.core.PageSiteMap;
import com.clouway.core.User;
import com.clouway.core.UserSessionsRepository;
import com.google.inject.Provider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * When user is logout then hes session and <code>Cookie</code> is deleted from database.
 */
public class LogoutServlet extends HttpServlet {


  private final UserSessionsRepository userSessionsRepository;
  private final PageSiteMap pageSiteMap;
  private final Provider<CurrentUser> currentUserProvider;

  public LogoutServlet(UserSessionsRepository userSessionsRepository, PageSiteMap pageSiteMap,
                        Provider<CurrentUser> currentUserProvider) {

    this.userSessionsRepository = userSessionsRepository;
    this.pageSiteMap = pageSiteMap;
    this.currentUserProvider = currentUserProvider;
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    User user = currentUserProvider.get().getUser();

    userSessionsRepository.deleteSession(user.getUserID());

    resp.sendRedirect(pageSiteMap.loginPage());
  }
}
