package com.clouway.core;

/**
 * Created by clouway on 7/16/14.
 */
public interface SessionRepository {
  User authenticate(String sessionID, Clock date);

  void deleteBy(String sid);
}
