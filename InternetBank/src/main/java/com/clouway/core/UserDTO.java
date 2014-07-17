package com.clouway.core;

/**
 * Created by clouway on 7/16/14.
 */
public class UserDTO {
  private String name;
  private String password;

  public UserDTO(String name, String password) {

    this.name = name;
    this.password = password;
  }

  public UserDTO() {

  }


  public void setName(String name) {
    this.name = name;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public String getPassword() {
    return password;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    UserDTO userDTO = (UserDTO) o;

    if (name != null ? !name.equals(userDTO.name) : userDTO.name != null) return false;
    if (password != null ? !password.equals(userDTO.password) : userDTO.password != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
    result = 31 * result + (password != null ? password.hashCode() : 0);
    return result;
  }
}
