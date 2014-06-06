package com.clouway.persistents;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import org.junit.runners.model.Statement;

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
      statement.evaluate();
    } finally {
      dataSource.getPooledConnection().close();
    }
  }
}
