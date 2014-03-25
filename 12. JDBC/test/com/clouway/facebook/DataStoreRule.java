package com.clouway.facebook;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Created by clouway on 3/21/14.
 */
public class DataStoreRule implements TestRule {
  private BasicDataSource basicDataSource = new BasicDataSource();
  @Override
  public Statement apply(Statement statement, Description description) {
    return new DataStoreStatement(statement, basicDataSource);
  }

  public BasicDataSource getBasicDataSource() {
    return basicDataSource;
  }
}
