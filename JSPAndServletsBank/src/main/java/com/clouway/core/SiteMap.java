package com.clouway.core;

/**
 * Created by clouway on 6/9/14.
 */
public interface SiteMap {

  String mainPage();

  String loginPage();

  String register();

  String withdrawingPage();

  String depositPage();

  String viewAmountPage();

  String contentPage();

  String mainServlet();

  String emptyPage();

  String errorPage();

  String registerError();

  String loginError();

  String errorNotFound();
}
