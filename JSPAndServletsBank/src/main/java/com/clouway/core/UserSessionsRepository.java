package com.clouway.core;

import java.util.Date;

/**
 * Created by clouway on 6/2/14.
 */
public interface UserSessionsRepository {
  /**
   * Finds user that is associated with the provided session id.
   * @param sessionId the session id of the user
   * @param clock
   * @return the User that is associated with the provided session id.
   */
  User isUserExistBySession(String sessionId, Clock clock);

  void deleteSession(String userID);

  boolean isValidUserSession(String sessionID, Date date);

//  String getUserSessionIDByID(int userID);
}
