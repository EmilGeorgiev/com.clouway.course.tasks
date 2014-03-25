package com.clouway.facebook;

/**
 * Created by clouway on 3/21/14.
 */
public class User {
  private final int id;
  private final String password;
  private final String username;


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    User user = (User) o;

    if (id != user.id) return false;
    if (password != null ? !password.equals(user.password) : user.password != null) return false;
    if (username != null ? !username.equals(user.username) : user.username != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (password != null ? password.hashCode() : 0);
    result = 31 * result + (username != null ? username.hashCode() : 0);
    return result;
  }

  public int getId() {

    return id;
  }

  public String getPassword() {
    return password;
  }

  public String getUsername() {
    return username;
  }

  public User(int id, String password, String username) {
    this.id = id;
    this.password = password;
    this.username = username;
  }


}
