package com.clouway.manyrows;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.SQLException;

/**
 * Created by clouway on 3/24/14.
 */
public class MainManyRows {
  public static void main(String[] args) {
    BasicDataSource dataSource = new BasicDataSource();

    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    dataSource.setUrl("jdbc:mysql://localhost/dublicate");
    dataSource.setUsername("root");
    dataSource.setPassword("root");

    try {
      ManyRows manyRows = new ManyRows(dataSource.getConnection());
      manyRows.insertRows(1000000);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
