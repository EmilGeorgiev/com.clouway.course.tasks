package com.clouway.travel;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.runners.model.Statement;

/**
 * Created by clouway on 3/20/14.
 */
public class RepositoryStatement extends Statement {

  private final Statement statement;
  private BasicDataSource dataSource;

  public RepositoryStatement(Statement statement, BasicDataSource dataSource) {

    this.statement = statement;
    this.dataSource = dataSource;
  }

  @Override
  public void evaluate() throws Throwable {
    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    dataSource.setUrl("jdbc:mysql://localhost/travel");
    dataSource.setUsername("root");
    dataSource.setPassword("root");

    try {
      statement.evaluate();
    } finally {
      dataSource.close();
    }
  }
}
