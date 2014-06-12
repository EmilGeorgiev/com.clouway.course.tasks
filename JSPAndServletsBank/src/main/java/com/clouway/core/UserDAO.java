package com.clouway.core;

/**
 * Created by clouway on 5/26/14.
 */
public interface UserDAO {

  User getUser(String name, String password);

  SessionID authenticate(String userName, String userPassword);

  void register(String userName, String userPassword);
}
