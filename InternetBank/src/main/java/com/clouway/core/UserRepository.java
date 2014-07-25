package com.clouway.core;

/**
 * Created by clouway on 7/16/14.
 */
public interface UserRepository {
  String registerUserIfNotExist(UserDTO userDTO);

  String isUserExist(UserDTO userDTO);

}
