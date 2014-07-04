package com.clouway.persistent;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import org.junit.runners.model.Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by clouway on 7/3/14.
 */
public class RepositoryStatement extends Statement {
  private final Statement base;
  private final MysqlConnectionPoolDataSource dataSource;

  public RepositoryStatement(Statement base, MysqlConnectionPoolDataSource dataSource) {
    this.base = base;
    this.dataSource = dataSource;
  }

  @Override
  public void evaluate() throws Throwable {
    dataSource.setServerName("localhost");
    dataSource.setPassword("root");
    dataSource.setUser("root");
    dataSource.setDatabaseName("Book_Store");

    cleanTables();

    base.evaluate();
  }

  private void cleanTables() {
    PreparedStatement preparedStatement = null;

    Connection connection = null;

    try {

      connection = dataSource.getConnection();

      connection.setAutoCommit(false);

      preparedStatement = connection.prepareStatement("DELETE FROM Books");

      preparedStatement.executeUpdate();

      preparedStatement = connection.prepareStatement("ALTER TABLE Books AUTO_INCREMENT = 1");

      preparedStatement.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (connection != null) {
          connection.setAutoCommit(true);
        }
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

}
