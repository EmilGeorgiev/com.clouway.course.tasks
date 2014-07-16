package com.clouway.http;

import com.clouway.core.SessionRepository;
import com.clouway.core.SiteMap;
import com.clouway.core.User;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

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
 * Created by clouway on 7/16/14.
 */
@Singleton
public class AuthenticatedFilter implements Filter{
  private final SessionRepository sessionRepository;
  private final Provider<User> userProvider;
  private final SiteMap siteMap;

  @Inject
  public AuthenticatedFilter(SessionRepository sessionRepository,
                             Provider<User> userProvider,
                             SiteMap siteMap) {

    this.sessionRepository = sessionRepository;
    this.userProvider = userProvider;
    this.siteMap = siteMap;
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    HttpServletRequest servletRequest = (HttpServletRequest) request;

    HttpServletResponse servletResponse = (HttpServletResponse) response;

    if (!sessionRepository.authenticateSession(userProvider.get().getUserSession())) {
      servletResponse.sendRedirect(siteMap.loginPage());
    }

    chain.doFilter(servletRequest, servletResponse);
  }

  @Override
  public void destroy() {

  }
}
