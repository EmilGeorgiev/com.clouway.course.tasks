package com.clouway.http;

import com.clouway.core.SessionRepository;
import com.clouway.core.SiteMap;
import com.clouway.core.UserDTO;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.sitebricks.At;
import com.google.sitebricks.http.Post;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by clouway on 7/17/14.
 */
@At("/loginController")
@Singleton
public class LoginController {

  private final SessionRepository sessionRepository;
  private final HttpServletResponse response;
  private final SiteMap siteMap;
  private UserDTO userDTO = new UserDTO();

  @Inject
  public LoginController(SessionRepository sessionRepository, HttpServletResponse response, SiteMap siteMap) {
    this.sessionRepository = sessionRepository;
    this.response = response;

    this.siteMap = siteMap;
  }

  @Post
  public void login() {

    //Check whether user data is valid and return sessionID or null if not.
    String sid = sessionRepository.authenticateUser(userDTO);

    if (sid != null) {

      response.addCookie(new Cookie(siteMap.sid(), sid));

      try {
        response.sendRedirect(siteMap.mainController());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

}
