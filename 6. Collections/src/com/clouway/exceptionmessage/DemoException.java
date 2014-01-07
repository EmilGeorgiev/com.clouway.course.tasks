package com.clouway.exceptionmessage;

/**
 * Created with IntelliJ IDEA.
 * User: clouway
 * Date: 12/13/13
 * Time: 10:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class DemoException {

  public static void main(String[] args) {
    ExceptionMessageManager exceptionManager = new ExceptionMessageManager();
    exceptionManager.registerErrorMessage("invalid number", "Невалиден номер на дебитната карта");
    exceptionManager.registerErrorMessage("error EGN", "Грешно ЕГН");
    exceptionManager.registerErrorMessage("invalid code", "Невалиден пощенски код");

    String errorMessage = exceptionManager.getErrorMessage();
    System.out.println(errorMessage);
  }
}
