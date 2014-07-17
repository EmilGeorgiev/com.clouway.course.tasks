package com.clouway.http;

import com.clouway.core.UserDTO;
import com.clouway.core.UserRepository;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.sitebricks.At;
import com.google.sitebricks.Show;
import com.google.sitebricks.headless.Service;
import com.google.sitebricks.http.Post;

/**
 * Created by clouway on 7/16/14.
 */
@At("/registerController")
@Service
@Show("/LoginController.html")
@Singleton
public class RegisteredController {

  private UserDTO userDTO = new UserDTO();
  private final UserRepository userRepository;
  private String authenticateMessage;

  @Inject
  public RegisteredController(UserRepository userRepository) {

    this.userRepository = userRepository;

  }

  @Post
  public void registered() {

    authenticateMessage = userRepository.registerUserIfNotExist(userDTO);

  }

  public String getAuthenticateMessage() {
    return authenticateMessage;
  }

  public void setAuthenticateMessage(String authenticateMessage) {
    this.authenticateMessage = authenticateMessage;
  }
}
