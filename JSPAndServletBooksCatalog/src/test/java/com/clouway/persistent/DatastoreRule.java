package com.clouway.persistent;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Created by clouway on 7/3/14.
 */
public class DatastoreRule implements TestRule {

  private MysqlConnectionPoolDataSource dataSource = new MysqlConnectionPoolDataSource();

  @Override
  public Statement apply(Statement base, Description description) {
    return new RepositoryStatement(base, dataSource);
  }

  public MysqlConnectionPoolDataSource getDataSource() {
    return dataSource;
  }
}
