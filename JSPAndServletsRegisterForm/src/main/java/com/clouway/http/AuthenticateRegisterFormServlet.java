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
import java.util.Map;


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

    Map<String, String[]> parameters = req.getParameterMap();

    //Check whether user data is valid according to requirements on webapp.
    for(Map.Entry<String, String[]> parameter : parameters.entrySet()) {

      String result = authorizationFormData.validateUserData(parameter.getKey(), parameter.getValue()[0]);

      //Set attribute with <code>result<code> messages which display on browser
      // and inform user whether its data are correct or is not.
      req.setAttribute(parameter.getKey(), result);

      //Allow user data to be retained after refresh page.
      req.setAttribute(parameter.getKey() + registerFormMessages.value(), parameter.getValue()[0]);
    }

    RequestDispatcher requestDispatcher = req.getRequestDispatcher(siteMap.registerForm());

    requestDispatcher.forward(req, resp);

  }
}
