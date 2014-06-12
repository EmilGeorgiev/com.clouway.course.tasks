package com.clouway.persistents;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import org.junit.runners.model.Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by clouway on 6/6/14.
 */
public class RepositoryStatement extends Statement {
  private final Statement statement;
  private final MysqlConnectionPoolDataSource dataSource;

  public RepositoryStatement(Statement statement, MysqlConnectionPoolDataSource dataSource) {

    this.statement = statement;
    this.dataSource = dataSource;
  }


  @Override
  public void evaluate() throws Throwable {
    dataSource.setServerName("localhost");
    dataSource.setPassword("root");
    dataSource.setUser("root");
    dataSource.setDatabaseName("Bank");

    try {
      cleanTableAccounts();


      statement.evaluate();
    } finally {
      //dataSource.getPooledConnection().close();
    }
  }

  private void cleanTableAccounts() {
    PreparedStatement preparedStatement = null;

    Connection connection = null;

    try {

      connection = dataSource.getConnection();

      connection.setAutoCommit(false);

      preparedStatement = connection.prepareStatement("DELETE FROM Accounts");

      preparedStatement.executeUpdate();

      preparedStatement = connection.prepareStatement("ALTER TABLE Accounts AUTO_INCREMENT = 1");

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

    cleanTransferHistory();
  }

  private void cleanTransferHistory() {
    PreparedStatement preparedStatement = null;



    Connection connection = null;
    try {

      connection = dataSource.getConnection();

      connection.setAutoCommit(false);

      preparedStatement = connection.prepareStatement("DELETE FROM TransferHistory");

      preparedStatement.executeUpdate();
      preparedStatement = connection.prepareStatement("ALTER TABLE TransferHistory AUTO_INCREMENT = 1");

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

    cleanSession();
  }

  private void cleanSession() {
    PreparedStatement preparedStatement = null;

    try {

      preparedStatement = dataSource.getConnection().prepareStatement("DELETE FROM Session");

      preparedStatement.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    cleanUsers();
  }

  private void cleanUsers() {
    PreparedStatement preparedStatement = null;

    Connection connection = null;
    try {

      connection = dataSource.getConnection();

      connection.setAutoCommit(false);

      preparedStatement = connection.prepareStatement("DELETE FROM Users");

      preparedStatement.executeUpdate();
      preparedStatement = connection.prepareStatement("ALTER TABLE Users AUTO_INCREMENT = 1");

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
