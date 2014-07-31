package com.clouway.core;

/**
 * Liaise with database.
 */
public interface SessionRepository {

  //SMELL repository object must contains operation related with CRUD.
  User authenticate(String sessionID, Clock clock);

  void deleteBy(String sid);
}
