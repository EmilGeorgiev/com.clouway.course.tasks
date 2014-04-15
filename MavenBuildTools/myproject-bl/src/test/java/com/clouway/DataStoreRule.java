package com.clouway;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Created by clouway on 4/15/14.
 */
public class DataStoreRule implements TestRule{
 private BasicDataSource basicDataSource = new BasicDataSource();

  @Override
  public Statement apply(Statement statement, Description description) {
    return new DataStoreStatement(statement, basicDataSource);
  }
}
