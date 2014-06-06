package com.clouway.objects;

/**
 * Created by clouway on 5/26/14.
 */
public interface UserDAO {

  User getUser(String name, String password);

  boolean isUserExist(String userName, String userPassword);

  void register(String userName, String userPassword);
}
