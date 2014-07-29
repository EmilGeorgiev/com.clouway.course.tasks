package com.clouway.http;

import com.clouway.core.SiteMap;
import com.clouway.core.UserDTO;
import com.clouway.core.UserEntity;
import com.clouway.core.UserRepository;
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

  private final UserRepository userRepository;
  private final HttpServletResponse response;
  private final SiteMap siteMap;
  private UserDTO userDTO = new UserDTO();
  private String userMessage;
  private boolean isShowMessage = false;

  @Inject
  public LoginController(UserRepository userRepository,
                         HttpServletResponse response, SiteMap siteMap) {
    this.userRepository = userRepository;
    this.response = response;

    this.siteMap = siteMap;
  }

  /**
   * login user and preserves user's session in <code>Cookie</code>
   * @throws IOException
   */
  @Post
  public String login() throws IOException {

    UserEntity userEntity = new UserEntity(userDTO.getName(), userDTO.getPassword());

    //Check whether user data is valid and return sessionID or null if not.
    String sid = userRepository.isExist(userEntity);

    if (sid != null) {

      isShowMessage = false;

      response.addCookie(new Cookie(siteMap.sid(), sid));

      return siteMap.mainController();

    }

    userMessage = "Invalid user name or password";
    isShowMessage = true;
    return siteMap.loginController();
  }

  public UserDTO getUserDTO() {
    return userDTO;
  }

  public void setUserDTO(UserDTO userDTO) {
    this.userDTO = userDTO;
  }

  public String getUserMessage() {
    return userMessage;
  }

  public void setUserMessage(String userMessage) {
    this.userMessage = userMessage;
  }

  public boolean getIsShowMessage() {
    return isShowMessage;
  }
}
