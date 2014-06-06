package com.clouway.constants;

/**
 * Created by clouway on 5/29/14.
 */
public class Foo implements BankAccountMessages {
  @Override
  public String connections() {
    return null;
  }

  @Override
  public String mainPage() {
    return Constants.MAIN_PAGE;
  }

  @Override
  public String login() {
    return null;
  }

  @Override
  public String register() {
    return null;
  }

  @Override
  public String currentAccountMessage() {
    return Constants.CURRENT_AMOUNT;
  }

  @Override
  public String withdrawingAmount() {
    return Constants.WITHDRAWING_AMOUNT;
  }

  @Override
  public String depositAmount() {
    return Constants.DEPOSIT_AMOUNT;
  }

  @Override
  public String logout() {
    return null;
  }

  @Override
  public String userNameValidationPattern() {
    return null;
  }

  @Override
  public String passwordValidationPattern() {
    return null;
  }

  @Override
  public String footer() {
    return null;
  }

  @Override
  public String withdrawingPage() {
    return Constants.WITHDRAWING_PAGE;
  }

  @Override
  public String success() {
    return Constants.SUCCESS_MESSAGE;
  }

  @Override
  public String error() {
    return Constants.ERROR_MESSAGE;
  }

  @Override
  public String userName() {
    return Constants.USER_NAME;
  }

  @Override
  public String userPassword() {
    return Constants.USER_PASSWORD;
  }

  @Override
  public String cookieName() {
    return Constants.COOKIE_NAME;
  }
}
