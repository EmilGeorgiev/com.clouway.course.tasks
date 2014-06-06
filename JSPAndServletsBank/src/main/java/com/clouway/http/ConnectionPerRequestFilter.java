package com.clouway.http;


import com.google.inject.Singleton;
import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.sql.PooledConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by clouway on 6/6/14.
 */
@Singleton
public class ConnectionPerRequestFilter implements Filter {

  public static final ThreadLocal<Connection> CONNECTION = new ThreadLocal<Connection>();

  private MysqlConnectionPoolDataSource dataSource = new MysqlConnectionPoolDataSource();

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    dataSource.setUser("root");
    dataSource.setPassword("root");
    dataSource.setDatabaseName("Bank");
    dataSource.setServerName("localhost");

  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    try {
      if (CONNECTION.get() == null) {

        Connection connection = getConnection();

        CONNECTION.set(connection);

      }

      filterChain.doFilter(servletRequest, servletResponse);

      if (CONNECTION.get() != null) {

        CONNECTION.get().close();

        CONNECTION.set(null);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void destroy() {

  }

  private Connection getConnection() throws SQLException {
    PooledConnection pooledConnection = dataSource.getPooledConnection();

    return pooledConnection.getConnection();
  }
}
