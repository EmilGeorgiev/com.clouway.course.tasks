package com.clouway.action.memory;

import com.clouway.objects.User;
import com.clouway.objects.UserDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clouway on 5/30/14.
 */
public class InMemoryUserDAO implements UserDAO {

  private List<User> userList = new ArrayList<User>();

  private int userID = 1;

  @Override
  public User getUser(String name, String password) {

    for (User user : userList) {
      if(user.getPassword().equals(password) && user.getUserName().equals(name)) {
        return user;
      }
    }

    return null;
  }

  @Override
  public boolean isUserExist(String userName, String userPassword) {

    for (User user : userList) {
      if(user.getPassword().equals(userPassword) && user.getUserName().equals(userName)) {
        return true;
      }
    }

    return false;
  }

  @Override
  public void register(String userName, String userPassword) {
    userList.add(new User(userName, userPassword, userID));
    userID++;
  }
}
