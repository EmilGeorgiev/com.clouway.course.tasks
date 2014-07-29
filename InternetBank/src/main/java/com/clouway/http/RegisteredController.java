package com.clouway.http;

import com.clouway.core.UserDTO;
import com.clouway.core.UserEntity;
import com.clouway.core.UserRepository;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.sitebricks.At;
import com.google.sitebricks.http.Post;

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

  /**
   * Register new user if it not exist and return message on user.
   */
  @Post
  public void registered() {

    UserEntity userEntity = new UserEntity(userDTO.getName(), userDTO.getPassword());

    registeredMessage = userRepository.registerUserIfNotExist(userEntity);

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
