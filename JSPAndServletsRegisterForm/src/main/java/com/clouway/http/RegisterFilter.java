package com.clouway.http;

import com.clouway.core.RegisterFormMessages;
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


@Singleton
public class RegisterFilter implements Filter {

  private final RegisterFormMessages formMessages;

  @Inject
  public RegisterFilter(RegisterFormMessages formMessages) {

    this.formMessages = formMessages;

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
