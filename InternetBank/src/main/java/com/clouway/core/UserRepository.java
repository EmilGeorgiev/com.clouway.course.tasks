package com.clouway.core;

/**
 *
 */
public interface UserRepository {

  /**
   * Register user in database if not exist.
   * @param userEntity user object who saved.
   * @return message whether registration is success or not.
   */
  String registerUserIfNotExist(UserEntity userEntity);

  /**
   * Check whether user is exist in database.
   * @param userEntity user object who checked.
   * @return session id if user exist
   */
  String isExist(UserEntity userEntity);

  /**
   * Find user.
   * @param session on user.
   * @return User if it is exist.
   */
  User findBySessionID(String session);
}
