package com.clouway.core;

/**
 * Created by clouway on 7/15/14.
 */
public interface UserSessionsRepository {
  User isUserExistBySession(String value, Clock clock);

}
