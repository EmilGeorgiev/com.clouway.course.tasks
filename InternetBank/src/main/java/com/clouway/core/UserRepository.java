package com.clouway.core;

/**
 * Created by clouway on 7/16/14.
 */
public interface UserRepository {
  String registerUserIfNotExist(UserDTO userDTO);

  User findUser(UserDTO userDTO);

  User findUserBySessionID(String session);
}
