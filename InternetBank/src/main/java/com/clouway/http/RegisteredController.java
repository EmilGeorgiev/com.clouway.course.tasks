package com.clouway.http;

import com.clouway.core.UserDTO;
import com.clouway.core.UserRepository;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.sitebricks.At;
import com.google.sitebricks.http.Post;

/**
 * Created by clouway on 7/16/14.
 */
@At("/registeredController")
@Singleton
public class RegisteredController {

  private UserDTO userDTO = new UserDTO();
  private final UserRepository userRepository;
  private String registeredMessage = null;

  @Inject
  public RegisteredController(UserRepository userRepository) {

    this.userRepository = userRepository;

  }

  @Post
  public void registered() {

    registeredMessage = userRepository.registerUserIfNotExist(userDTO);

  }

  public String getRegisteredMessage() {
    return registeredMessage;
  }

  public void setRegisteredMessage(String registeredMessage) {
    this.registeredMessage = registeredMessage;
  }

  public UserDTO getUserDTO() {
    return userDTO;
  }

  public void setUserDTO(UserDTO userDTO) {
    this.userDTO = userDTO;
  }
}
