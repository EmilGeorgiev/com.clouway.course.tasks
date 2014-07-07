package com.clouway.http;

import com.clouway.core.SiteMap;
import com.clouway.core.Validator;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Singleton
public class ValidatorServlet extends HttpServlet {

  private final Validator validator;
  private final SiteMap siteMap;

  @Inject
  public ValidatorServlet(Validator validator,
                          SiteMap siteMap) {

    this.validator = validator;
    this.siteMap = siteMap;

  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doPost(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    //Check whether user data is valid according to requirements on webapp.
//    Map<String, UserMessage> userMessages = validator.validate(req.getParameterMap());
//
//    //This attribute contains messages for all fields of the registration form that display of user.
//    req.setAttribute(siteMap.messages(), userMessages);

    RequestDispatcher requestDispatcher = req.getRequestDispatcher(siteMap.registerForm());

    requestDispatcher.forward(req, resp);

  }
}
