package com.clouway.exceptionmessage;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: clouway
 * Date: 12/13/13
 * Time: 9:57 AM
 * To change this template use File | Settings | File Templates.
 */
public class ExceptionMessageManager {

  private Map<String, String> mapOfErrorMessage;

  public ExceptionMessageManager() {
    mapOfErrorMessage = new HashMap<String, String>();
  }

  public void registerErrorMessage(String messageCode, String message) {
    mapOfErrorMessage.put(messageCode, message);
  }

  public String getErrorMessage() {
    StringBuffer errorMessage = new StringBuffer();
    for (Map.Entry<String, String> message : mapOfErrorMessage.entrySet()) {
      errorMessage.append(message.getValue() + "\n");
    }
    return errorMessage.toString();
  }
}

