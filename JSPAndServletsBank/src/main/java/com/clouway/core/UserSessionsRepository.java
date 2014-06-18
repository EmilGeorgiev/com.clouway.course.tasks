package com.clouway.core;

/**
 * Created by clouway on 6/2/14.
 */
public interface UserSessionsRepository {
  /**
   * Finds user that is associated with the provided session id.
   * @param sessionId the session id of the user
   * @return the User that is associated with the provided session id.
   */
  User isUserExistBySession(String sessionId);

  void deleteSession(String userID);

  boolean isValidUserSession(String userID);

//  String getUserSessionIDByID(int userID);
}
