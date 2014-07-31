package com.clouway.core;

/**
 * Liaise with database.
 */
public interface SessionRepository {

  //SMELL repository object must contains operation related with CRUD.
  User authenticate(String sessionID, Clock date);

  void deleteBy(String sid);
}
