package com.clouway.manyrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by clouway on 3/24/14.
 */
public class ManyRows {

  private final Connection connection;

  public ManyRows(Connection connection) {
    this.connection = connection;
  }

  /**
   * Insert many rows in database.
   * @param countRows is number of rows which inserted.
   *
   * <pre>
   *  ManyRows manyRows = new ManyRows();
   *  manyRows.insertRows(1_000_000);
   * </pre>
   */
  public void insertRows(int countRows) {
    PreparedStatement preparedStatement = null;
    try {
      connection.setAutoCommit(false);

      preparedStatement = connection.prepareStatement("Insert into customers (first_name, last_name, age, city, country) " +
              "values (?, ?, ?, ?, ?)");
      for (int i = 0; i < countRows; i++) {

        preparedStatement.setString(1, "ivan");
        preparedStatement.setString(2, "Ivanov");
        preparedStatement.setInt(3, i);
        preparedStatement.setString(4, "Tarnovo");
        preparedStatement.setString(5, "Bulgaria");

        preparedStatement.executeUpdate();

      }
      connection.commit();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
