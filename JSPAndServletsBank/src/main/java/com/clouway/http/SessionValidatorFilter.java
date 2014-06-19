package com.clouway.http;

import com.clouway.core.Clock;
import com.clouway.core.SiteMap;
import com.clouway.core.UserSessionsRepository;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Check before every request whether session on the current user is valid or it is expired.
 * If session is valid request is allowed otherwise user is redirect to login page.
 */
@Singleton
public class SessionValidatorFilter implements Filter{

  private final SiteMap siteMap;
  private final UserSessionsRepository userSessionsRepository;
  private final Clock clock;
  private String excludeLoginServlet;
  private String excludeRegisterServlet;


  @Inject
  public SessionValidatorFilter(SiteMap siteMap,
                                UserSessionsRepository userSessionsRepository,
                                Clock clock) {
    this.siteMap = siteMap;
    this.userSessionsRepository = userSessionsRepository;

    this.clock = clock;
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    this.excludeLoginServlet = filterConfig.getInitParameter("excludeLoginServlet");
    this.excludeRegisterServlet = filterConfig.getInitParameter("excludeRegisterServlet");
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    HttpServletRequest request = (HttpServletRequest) servletRequest;

    HttpServletResponse response = (HttpServletResponse) servletResponse;

    String url = request.getRequestURI();

    if (matchExcludePattern(url)) {
      filterChain.doFilter(request, response);
      return;
    }

    if (!userSessionsRepository.isValidUserSession(getUserSessionID(request), clock.now())) {
      response.sendRedirect(siteMap.loginPage());
      return;
    }

    filterChain.doFilter(request, response);
  }



  @Override
  public void destroy() {

  }

  private String getUserSessionID(HttpServletRequest request) {
    Cookie[] cookies = request.getCookies();

    for(Cookie cookie : cookies) {
      if ("sid".equals(cookie.getValue())) {
        return cookie.getValue();
      }
    }
    return null;
  }

  private boolean matchExcludePattern(String url) {
    return url.equals(excludeLoginServlet) || url.equals(excludeRegisterServlet);
  }
}
