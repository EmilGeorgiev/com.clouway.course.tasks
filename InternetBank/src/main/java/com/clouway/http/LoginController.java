package com.clouway.http;

import com.clouway.core.LoginMessages;
import com.clouway.core.SiteMap;
import com.clouway.core.User;
import com.clouway.core.UserRepository;
import com.google.common.base.Optional;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.sitebricks.At;
import com.google.sitebricks.http.Get;
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
  private final LoginMessages loginMessages;
  private String userMessage;
  private boolean isShowMessage = false;

  @Inject
  public LoginController(UserRepository userRepository,
                         HttpServletResponse response,
                         SiteMap siteMap,
                         LoginMessages loginMessages) {

    this.userRepository = userRepository;
    this.response = response;
    this.siteMap = siteMap;
    this.loginMessages = loginMessages;
  }

  /**
   * login user and preserves user's session in <code>Cookie</code>
   *
   * @throws IOException
   */
  @Post
  public String login() throws IOException {

    User user = new User(userDTO.getName(), userDTO.getPassword());

    //Check whether user data is valid and return sessionID or null if not.
    Optional<String> sid = userRepository.login(user);

    if (sid.isPresent()) {

      response.addCookie(new Cookie(siteMap.sid(), sid.get()));

      return siteMap.mainController();

    }

    userMessage = loginMessages.failed();
    isShowMessage = true;
    return null;
  }

  @Get
  public void setMessage() {
    isShowMessage = false;
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

  public void setShowMessage(boolean isShowMessage) {
    this.isShowMessage = isShowMessage;
  }
}
