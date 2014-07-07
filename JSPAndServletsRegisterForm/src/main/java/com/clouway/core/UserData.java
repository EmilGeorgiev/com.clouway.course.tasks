package com.clouway.core;

/**
 * Created by clouway on 7/7/14.
 */
public class UserData {

  private String first_name = "";
  private String last_name = "";
  private int  user_egn;
  private int user_age;
  private String user_address = "";
  private String user_password = "";

  public UserData() {
  }

  public String getFirst_name() {
    return first_name;
  }

  public void setFirst_name(String first_name) {
    this.first_name = first_name;
  }

  public String getLast_name() {
    return last_name;
  }

  public void setLast_name(String last_name) {
    this.last_name = last_name;
  }

  public int getUser_egn() {
    return user_egn;
  }

  public void setUser_egn(int user_egn) {
    this.user_egn = user_egn;
  }

  public int getUser_age() {
    return user_age;
  }

  public void setUser_age(int user_age) {
    this.user_age = user_age;
  }

  public String getUser_address() {
    return user_address;
  }

  public void setUser_address(String user_address) {
    this.user_address = user_address;
  }

  public String getUser_password() {
    return user_password;
  }

  public void setUser_password(String user_password) {
    this.user_password = user_password;
  }
}
