package com.clouway.http;

import com.clouway.core.AuthorizationFormData;
import com.clouway.core.RegisterFormMessages;
import com.clouway.core.SiteMap;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;


@Singleton
public class AuthenticateRegisterFormServlet extends HttpServlet {
  private final AuthorizationFormData authorizationFormData;
  private final SiteMap siteMap;
  private final RegisterFormMessages registerFormMessages;


  @Inject
  public AuthenticateRegisterFormServlet(AuthorizationFormData authorizationFormData,
                                         SiteMap siteMap,
                                         RegisterFormMessages registerFormMessages) {

    this.authorizationFormData = authorizationFormData;
    this.siteMap = siteMap;
    this.registerFormMessages = registerFormMessages;

  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doPost(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    //Get all parameter names from register <code>form<code>.
   Enumeration<String> parameterNames = req.getParameterNames();

    //Check whether user data is valid according to requirements on webapp.
    while (parameterNames.hasMoreElements()) {
      String parameterName = parameterNames.nextElement();

      String parameter = req.getParameter(parameterName);

      String regex = authorizationFormData.getRegexForParameter(parameterName);

      if(authorizationFormData.isUserDataMatchToRegex(parameter, regex)) {

        req.setAttribute(parameterName, "wrong");

      } else {

        req.setAttribute(parameterName, "correct");

      }

      //Allow user data to be retained after refresh page.
      req.setAttribute(parameterName + registerFormMessages.value(), parameter);

    }

    //Return back to register page to view which user data is correct and which is not.
    RequestDispatcher requestDispatcher = req.getRequestDispatcher(siteMap.registerForm());

    requestDispatcher.forward(req, resp);

  }
}
