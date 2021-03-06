package com.clouway.facebook;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.runners.model.Statement;

/**
 * Created by clouway on 3/21/14.
 */
public class DataStoreStatement extends Statement {
  private final Statement statement;
  private BasicDataSource basicDataSource;

  public DataStoreStatement(Statement statement, BasicDataSource basicDataSource) {
    this.statement = statement;
    this.basicDataSource = basicDataSource;
  }

  @Override
  public void evaluate() throws Throwable {
    basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
    basicDataSource.setUrl("jdbc:mysql://localhost/facebook");
    basicDataSource.setUsername("root");
    basicDataSource.setPassword("root");

    try {
      statement.evaluate();
    } finally {
      basicDataSource.close();

    }
  }
}
