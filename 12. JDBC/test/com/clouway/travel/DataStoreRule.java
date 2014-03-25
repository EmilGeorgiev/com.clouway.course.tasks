package com.clouway.travel;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Created by clouway on 3/20/14.
 */
public class DataStoreRule implements TestRule {

  private final BasicDataSource basicDataSource = new BasicDataSource();

  @Override
  public Statement apply(Statement statement, Description description) {

    return new RepositoryStatement(statement, basicDataSource);
  }


  public BasicDataSource getBasicDataSource() {
    return basicDataSource;
  }


}
