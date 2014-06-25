package com.clouway.http;

import com.clouway.core.*;
import com.clouway.persistents.PersistentBankDAO;
import com.clouway.persistents.PersistentUserDAO;
import com.google.inject.Provides;
import com.google.inject.servlet.ServletModule;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by clouway on 6/17/14.
 */
public class HttpModule extends ServletModule {

  @Override
  protected void configureServlets() {

    Map<String, String> initParamSession = new HashMap<String, String>();
    initParamSession.put("excludeLoginServlet", "/loginServlet");
    initParamSession.put("excludeRegisterServlet", "/registerServlet");

    Map<String, String> initParamConnection = new HashMap<String, String>();
    initParamConnection.put("excludeLoginPage", "/loginPage.jsp");
    initParamConnection.put("excludeLoginStyle", "/loginStyle.css");

    filter("/*Servlet").through(SessionValidatorFilter.class, initParamSession);
    filter("/*").through(ConnectionPerRequestFilter.class, initParamConnection);

    serve("/loginServlet").with(LoginServlet.class);
    serve("/registerServlet").with(RegisterServlet.class);
    serve("/logoutServlet").with(LogoutServlet.class);
    serve("/transactionServlet").with(TransactionServlet.class);
    serve("/viewCurrentAmountServlet").with(ViewCurrentAmountServlet.class);
    serve("/includePageController").with(IncludePageController.class);
    serve("/mainServlet").with(MainServlet.class);


    bind(AccountBankDAO.class).to(PersistentBankDAO.class);
    bind(TransactionHistory.class).to(PersistentBankDAO.class);

    bind(UserSessionsRepository.class).to(PersistentUserDAO.class);
    bind(UserDAO.class).to(PersistentUserDAO.class);
    bind(AuthenticateService.class).to(PersistentUserDAO.class);
    bind(Clock.class).to(Time.class);

  }

  @Provides
  public Connection getConnection() {
    return ConnectionPerRequestFilter.CONNECTION.get();
  }
}

