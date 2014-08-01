package com.clouway.core;

import com.google.common.base.Optional;

/**
 *
 */
public interface UserRepository {

  /**
   * Register user in database if not exist.
   *
   * @param user user object who saved.
   * @return message whether registration is success or not.
   */
  RegistrationInfo register(User user);

  /**
   * Check whether user is exist in database.
   *
   * @param user user object who checked.
   * @return session id if user exist
   */
  Optional<String> login(User user);

  /**
   * Find a given user by session.
   *
   * @param session on user.
   * @return User if it is exist.
   */
  Optional<CurrentUser> findBySession(String session);
}
