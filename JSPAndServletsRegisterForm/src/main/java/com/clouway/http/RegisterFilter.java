package com.clouway.http;

import com.clouway.core.RegisterFormMessages;
import com.clouway.core.SiteMap;
import com.google.inject.Inject;
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
import java.util.Map;

/**
 * Created by clouway on 6/27/14.
 */
@Singleton
public class RegisterFilter implements Filter {


  private final RegisterFormMessages formMessages;
  private final SiteMap siteMap;

  @Inject
  public RegisterFilter(RegisterFormMessages formMessages, SiteMap siteMap) {

    this.formMessages = formMessages;
    this.siteMap = siteMap;
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;

    HttpServletResponse response = (HttpServletResponse) servletResponse;

    Map<String, String> registerMessages = formMessages.getMessages();

    for(Map.Entry<String, String> entry : registerMessages.entrySet()) {
      if (request.getAttribute(entry.getKey()) == null) {
        request.setAttribute(entry.getKey(), entry.getValue());
        request.setAttribute(entry.getKey() + formMessages.value(), formMessages.emptyValue());
      }
    }

    filterChain.doFilter(request, response);

  }

  @Override
  public void destroy() {

  }
}
