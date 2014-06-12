package com.clouway.persistents.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by clouway on 6/12/14.
 */
public class UserUtil  {

  private final Connection connection;

  public UserUtil(Connection connection) {
    this.connection = connection;
  }

  public void registerNewUser(String name, String password) {
    PreparedStatement preparedStatement = null;

   // PreparedStatement createAccount = null;

    try {

      //connection.setAutoCommit(false);

      preparedStatement =  connection.prepareStatement("INSERT INTO Users (name, password) " +
              "VALUE (?, ?)");

      preparedStatement.setString(1, name);
      preparedStatement.setString(2, password);

      preparedStatement.executeUpdate();


      preparedStatement = connection.prepareStatement("INSERT INTO Accounts (amount, user_id) " +
              "VALUES (?, ?)");

      preparedStatement.setInt(1, 0);
      preparedStatement.setInt(2, 1);

      preparedStatement.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        connection.setAutoCommit(true);

        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
