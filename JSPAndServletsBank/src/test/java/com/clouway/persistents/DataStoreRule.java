package com.clouway.persistents;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Created by clouway on 6/6/14.
 */
public class DataStoreRule implements TestRule {

  private MysqlConnectionPoolDataSource dataSource = new MysqlConnectionPoolDataSource();

  @Override
  public Statement apply(Statement statement, Description description) {
    return new RepositoryStatement(statement, dataSource);
  }

  public MysqlConnectionPoolDataSource getDataSource() {
    return dataSource;
  }
}
