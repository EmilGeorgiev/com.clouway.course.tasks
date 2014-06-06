package com.clouway.objects;

/**
 * Created by clouway on 5/26/14.
 */
public class User {

  private String userName;
  private String password;

  private int userID;

  public User(String userName, String password, int userID) {
    this.userName = userName;
    this.password = password;
    this.userID = userID;
  }



  public String getPassword() {
    return password;
  }

  public String getUserName() {
    return userName;
  }

  public int getUserID() {
    return this.userID;
  }
}
