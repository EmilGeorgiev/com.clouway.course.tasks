package com.clouway.http;

import com.clouway.core.Clock;
import com.clouway.core.SessionRepository;
import com.clouway.core.SiteMap;
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

@Singleton
public class SecurityFilter implements Filter{

  private final SessionRepository sessionRepository;
  private final SiteMap siteMap;
  private final Clock clock;

  @Inject
  public SecurityFilter(SessionRepository sessionRepository,
                        SiteMap siteMap,
                        Clock clock) {

    this.sessionRepository = sessionRepository;
    this.siteMap = siteMap;
    this.clock = clock;
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  /**
   * For each request from the user checks whether the current user session has expired.
   */
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    HttpServletRequest servletRequest = (HttpServletRequest) request;

    HttpServletResponse servletResponse = (HttpServletResponse) response;

    String sessionID = null;

    Cookie[] cookies = servletRequest.getCookies();

    if (cookies != null) {
      for(Cookie cookie : cookies) {
        if("sid".equals(cookie.getName())) {
          sessionID = cookie.getValue();
        }
      }
    }

    if (!sessionRepository.authenticate(sessionID)) {
      servletResponse.sendRedirect(siteMap.loginController());
    }

    chain.doFilter(servletRequest, servletResponse);
  }

  @Override
  public void destroy() {

  }
}
