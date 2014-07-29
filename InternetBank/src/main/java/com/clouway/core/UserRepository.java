package com.clouway.core;

/**
 * Created by clouway on 7/16/14.
 */
public interface UserRepository {
  String registerUserIfNotExist(UserEntity userEntity);

  String isExist(UserEntity userEntity);

  User findUserBySessionID(String session);
}
