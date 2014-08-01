package com.clouway.core;

/**
 * Liaise with database.
 */
public interface SessionRepository {

  //SMELL repository object must contains operation related with CRUD.
  boolean authenticate(String sessionID);

}
