package com.clouway.http;

import com.clouway.core.CurrentUser;
import com.clouway.core.PageSiteMap;
import com.clouway.core.SessionID;
import com.clouway.core.User;
import com.clouway.core.UserSessionsRepository;
import com.google.inject.Provider;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Check before every request whether session on the current user is valid or it is expired.
 * If session is valid request is allowed otherwise user is redirect to login page.
 */
public class SessionValidatorFilter implements Filter{

  private final PageSiteMap pageSiteMap;
  private final UserSessionsRepository userSessionsRepository;
  private final Provider<CurrentUser> currentUserProvider;

  public SessionValidatorFilter(PageSiteMap pageSiteMap, UserSessionsRepository userSessionsRepository, Provider<CurrentUser> currentUserProvider) {
    this.pageSiteMap = pageSiteMap;
    this.userSessionsRepository = userSessionsRepository;
    this.currentUserProvider = currentUserProvider;
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;

    HttpServletResponse response = (HttpServletResponse) servletResponse;

    User user = currentUserProvider.get().getUser();

    SessionID sessionID = user.getSessionID();

    if (!userSessionsRepository.isValidUserSession(sessionID.getSessionID())) {
      response.sendRedirect(pageSiteMap.loginPage());
    }

    filterChain.doFilter(request, response);
  }

  @Override
  public void destroy() {

  }
}
