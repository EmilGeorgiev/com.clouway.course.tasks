package com.clouway.http;

import com.clouway.core.CurrentUser;
import com.clouway.core.PageSiteMap;
import com.clouway.core.UserSessionsRepository;
import com.google.inject.Provider;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Created by clouway on 6/11/14.
 */
public class SessionValidatorFilter implements Filter{
  public SessionValidatorFilter(PageSiteMap pageSiteMap, UserSessionsRepository userSessionsRepository, Provider<CurrentUser> currentUserProvider) {
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

  }

  @Override
  public void destroy() {

  }
}
