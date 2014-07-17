package com.clouway.core;

import java.util.Date;

/**
 * Created by clouway on 7/16/14.
 */
public interface SessionRepository {
  User authenticateSession(String sessionID, Clock date);

  void deleteSessionByID(String sid);
}
